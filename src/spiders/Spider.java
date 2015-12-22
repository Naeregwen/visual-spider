package spiders;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.Vector;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.NodeClassFilter;
import org.htmlparser.filters.OrFilter;
import org.htmlparser.tags.ImageTag;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.tags.TitleTag;
import org.htmlparser.util.NodeIterator;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;
import org.htmlparser.util.Translate;

import spiders.components.ColoredTee;
import spiders.constants.Constants;
import spiders.datas.Profile;
import spiders.datas.Statistics;
import spiders.datas.VisitedURL;
import spiders.util.URLExt;

public class Spider extends Thread {

	// basic properties
	private Profile profile;
	// status tracking
	private Statistics statistics;
	private SortedMap<VisitedURL, String> visitedURL = new TreeMap<VisitedURL, String>();
	private Vector<URL> URLToVisit = new Vector<URL>();
	private SortedMap<VisitedURL, Boolean> URLToVisitInDepth = new TreeMap<VisitedURL, Boolean>();
	private Date startTime, endTime;
	// user communication and control
	private ColoredTee tee;
	// thread status 
	public enum Status { RUNNING, PAUSED, STOPPED }
	private Thread blinker;
	private boolean suspended = true;
	private boolean interrupted = false;
	
	// robots politness file
	//private static final String ROBOT = "robot.txt";
	
	public Spider(Profile parameters, SpiderView view) {
		// initialize spider properties
		this.profile = parameters;
		statistics = new Statistics();
		this.tee = new ColoredTee(view, parameters);
	}

	public static void main(String[] args) {		
        (new Spider(new Profile(), null)).start(); // Console default demo
	}
	
	public void start() {
		suspended = false;
        blinker = new Thread(this);
        blinker.start();
    }
	
	public void run() {

		// mark job start
		if (profile.getVerbose()) showStart();
		// ignite the spider
		if ((this.profile.getMethod() == Constants.METHOD.BREADTH_FIRST && URLToVisit.size() <= 0)
				|| this.profile.getMethod() == Constants.METHOD.DEPTH_FIRST && URLToVisitInDepth.size() <= 0)
			ignite();
		// crawl the web
        switch (profile.getMethod()) {
		// breadth first method
		case BREADTH_FIRST:
			while (blinker == Thread.currentThread() && (profile.getMaxURLtoVisit() <= 0 || visitedURL.size() < profile.getMaxURLtoVisit()) && URLToVisit.size() != 0) {
				checkSuspended();
				if (visitedURL.get(new VisitedURL(file(URLToVisit.get(0)))) == null) 
					if (visit(URLToVisit.get(0), visitedURL.size()+1))
				        statistics.addUsed();
				URLToVisit.remove(0);
			}
			break;
		// depth first method
		case DEPTH_FIRST:
			int visitedURL = 0; 
			while (blinker == Thread.currentThread() && (profile.getMaxURLtoVisit() <= 0 || visitedURL < profile.getMaxURLtoVisit())) {
				checkSuspended();
				Set<Entry<VisitedURL, Boolean>> set = URLToVisitInDepth.entrySet();
				Iterator<Entry<VisitedURL, Boolean>> i = set.iterator();
				while (i.hasNext()) {
					Entry<VisitedURL, Boolean> entry = i.next();
					if (!entry.getValue()) {			
						if (visit(entry.getKey().toURL(), visitedURL+1)) visitedURL++;
						entry.setValue(true); // mark as used
				        statistics.addUsed();
						break;
					}
				}							
			}
			break;			
		}
        
		// mark job end
		if (profile.getVerbose()) showEnd();
		stopSearch();
    		
		// summarize crawling ?
		if (profile.getVerbose() && profile.getSummarizeCrawling()) {
			tee.writeMessage("\nCrawling summary :"
					+statistics.getUsed()+" url used, "
					+statistics.getRead()+" url read, "
					+statistics.getError()+" url in error\n", true);
			Set<Entry<VisitedURL, String>> set = visitedURL.entrySet();
			Iterator<Entry<VisitedURL, String>> i = set.iterator();
			int num = 1;
			profile.setURLDecimalFormat(statistics.getMaxURLPerPage());
			profile.setIMGDecimalFormat(statistics.getMaxIMGPerPage());
			while (i.hasNext()) {
				Entry<VisitedURL, String> entry = i.next();
				tee.writeMessage(profile.format(Constants.FORMAT.DF, num++) + " (" + 
						profile.format(Constants.FORMAT.UDF, entry.getKey().getnLinks())
						+ (profile.getImagesCount() ? 
						(", " + profile.format(Constants.FORMAT.IDF, entry.getKey().getnImages())) : "") + ")"
						+ " url : " + entry.getKey().toString() + " - title : " + format(entry.getValue()), true);
			}		
		}
	}
	
	private void ignite() {
		resetStats();
		if (this.profile.getMethod() == Constants.METHOD.BREADTH_FIRST)
			URLToVisit.add(this.profile.getStartSite());
		else
			URLToVisitInDepth.put(new VisitedURL(profile.getStartSite().toString()), false);
	}
	
	public void resetStats() {
		URLToVisit.removeAllElements();
		URLToVisitInDepth = new TreeMap<VisitedURL, Boolean>();
		visitedURL = new TreeMap<VisitedURL, String>();
		statistics.clear();
	}
	
	public void setSearch(Vector<URL> URLToVisit, Vector<VisitedURL> visitedURL) {
		this.URLToVisit = URLToVisit;
		for (VisitedURL url : visitedURL) {
			this.visitedURL.put(url, url.getTitle());
		}
	}
	
	/**
	 * url content parsing
	 * 
	 */
	// pattern to detect non html encoding
	private static final Pattern NONPERCENT = Pattern.compile("%([^\\p{XDigit}])");
	
	private Boolean visit(URL url, int position) {

		// parse this url
		String file = file(url);
		String host = host(url);
		VisitedURL newURL = new VisitedURL(file);
		String title = null;
		int depth = depth(url);		
		if (!isValid(newURL, depth)) {
			statistics.addError();
			return false;
		}
		try {
			
			if (profile.getVerbose() && profile.getShowVisitedURL()) tee.writeInfos("reading : " + url.toString(), true);
			// instanciate a new htmlparser for this url
			Parser parser = new Parser(url.toString());
			// build combinated OrFilter for one pass parsing
			NodeList list = new NodeList ();
			NodeFilter filter =
			    new OrFilter (
			    	new NodeClassFilter (TitleTag.class),
			    	profile.getImagesCount() ? 
			    	new OrFilter (new NodeClassFilter (ImageTag.class),new NodeClassFilter (LinkTag.class)) :
			    	new NodeClassFilter (LinkTag.class));
			NodeIterator e = parser.elements (); 
			while (e.hasMoreNodes ()) e.nextNode().collectInto (list, filter);
			// count characters in page
			newURL.addChars(parser.getLexer().getPage().getText().length());
			
			// parsing main loop
			for (int i = 0; i < list.size(); i++) {
								
				// parsing an image
				if (list.elementAt(i) instanceof ImageTag)
					newURL.addImages(1);
				
				// parsing a title
				else if (list.elementAt(i) instanceof TitleTag) {
					title = ((TitleTag) list.elementAt(i)).getTitle();
					// encode all "%([^\\p{XDigit}])" sequences to prevent false encoding exception from decoder
					Matcher m = NONPERCENT.matcher(title);
					if (m.find()) title = m.replaceAll("&#37;"+m.group(1));			
					// decode title from application/x-www-form-urlencoded MIME format
					try {
						URLDecoder.decode(title, parser.getEncoding());
					} catch (UnsupportedEncodingException uee) {
						if (profile.getVerbose() && profile.getShowEncodingProblems())
							setError(newURL, uee.getLocalizedMessage(), true);
					} catch (IllegalArgumentException iae) {
						if (profile.getVerbose() && profile.getShowIllegalArguments())
							setError(newURL, iae.getLocalizedMessage() + " in title : '" + title + "'", true);
					}
					// decode html entities and format title
					title = format(Translate.decode (title));
					newURL.setTitle(title != null ? title : "");
					
				// parsing a link
				} else if (list.elementAt(i) instanceof LinkTag) {
					
	            	LinkTag tag = (LinkTag) list.elementAt(i);
	            	// ignore "#" href
	            	if (tag.getAttributeEx("href") == null || tag.getAttributeEx("href").getValue().equals("#")) continue; 
	            	String href = tag.extractLink();
	            	// Strip url fragment
	            	int fragmentIndex;
	            	if ((fragmentIndex = href.indexOf("#")) == 0) href = "";
	            	else if (fragmentIndex != -1) href = href.substring(0, fragmentIndex -1);
	            	// Check url syntax
	            	if (!href.startsWith("http://"))
	        			href = href.startsWith("www.") ? url.getProtocol() + "://" + href : 
	        				href.startsWith("/") ? host + href : host + "/" + href;
	            	VisitedURL URLToInsert = new VisitedURL(href);
	            	try {
	            		// keep unvisited URL once only
	            		URL urlFound = new URL(href);
	            		URLToInsert = new VisitedURL(file(urlFound));
	            		if (!isValid(URLToInsert, depth(URLToInsert.toURL()))) continue;
	            		switch (profile.getMethod()) {
	            		// breadth first method
	            		case BREADTH_FIRST:
	            			if (visitedURL.get(URLToInsert) != null) continue;
	            			if (!URLToVisit.contains(urlFound) 
	            					&& (profile.getIncludedURL().contains(host(urlFound)) 
	            							|| !profile.getExcludedURL().contains(host(urlFound)))) {
	            				URLToVisit.add(urlFound);  
	            			} else continue;
	            			break;
	            		// depth first method
	            		case DEPTH_FIRST:
	            			if (URLToVisitInDepth.containsKey(URLToInsert) 
	            					|| (!profile.getIncludedURL().contains(host(urlFound)) 
	            							&& profile.getExcludedURL().contains(host(urlFound)))) 
	            				continue;
	            			if (href.length() > 1 && href.charAt(href.length()-1) == '/'
	            				&& URLToVisitInDepth.containsKey(new VisitedURL(file(new URL(href.substring(0, href.length()-2))))))
	            				continue;
	            			URLToVisitInDepth.put(newURL, false); 
	            			break;
	            		}            			
					} catch (MalformedURLException eURL) {
						if (profile.getVerbose() && profile.getShowInvalidURL()) 
							setError(URLToInsert, "invalid url : " + href + " " + format(list.elementAt(i).toHtml()), true);
					}
					newURL.addLinks(1);	
				}
			}
			// mark URL (base formatted) as visited
			visitedURL.put(newURL, title);
			if (profile.getVerbose()) 
				tee.writeMessage(profile.format(Constants.FORMAT.DF, position) + " (" + depth + ") url : " + url.toString() + " - title : " + title, true);
			else
				if (profile.getUseConsole()) System.out.print(".");
			// update max url/page
			if (newURL.getnLinks() > statistics.getMaxURLPerPage()) 
				statistics.setMaxURLPerPage(newURL.getnLinks());
			// update max images/page
			if (newURL.getnImages() > statistics.getMaxIMGPerPage()) 
				statistics.setMaxIMGPerPage(newURL.getnImages());
			// update view
			if (!newURL.hasError()) {
				statistics.addRead();
				newURL.setRead(true);
			}
			tee.addURL(newURL, position);
			
        } catch (ParserException e) {
        	setError(newURL, e.getLocalizedMessage(), true);
        }	
        return true;
	}
	
	private Boolean isValid(VisitedURL url, int depth) {
		// skip everything but allowed extension files
		String path = url.toURL().getPath();
		int lastDot = path.lastIndexOf(".");
		if(lastDot > 0 && !profile.getAllowedExtensions().contains(path.substring(lastDot))) {
//		if (!parameters.allowedExtensions.contains(getExtension(url))) {
			if (profile.getVerbose() && profile.getShowSkippedURL()) setError(url, "Skipping URL : " + url + " - unallowed extension", true);
			if (profile.getMethod() == Constants.METHOD.DEPTH_FIRST) {
				URLToVisitInDepth.remove(new VisitedURL(url.toString()));
			}
			return false;
		}
		// skip invalid mailto (html error)
		if (url.toString().startsWith("mailto:")) {
			if (profile.getVerbose() && profile.getShowSkippedURL()) setError(url, "Skipping URL : " + url + " - invalid mailto", true);
			if (profile.getMethod() == Constants.METHOD.DEPTH_FIRST) {
				URLToVisitInDepth.remove(new VisitedURL(url.toString()));
			}
			return false; 
		}
		// skip url not in same domain
		if (profile.getStayInDomain()) {
			URLExt.DOMAIN domain = URLExt.isSameDomain(url.toURL(), profile.getBaseDomain());
			switch (domain) {
			case NOT_SAME: 
				if (profile.getVerbose() && profile.getShowInvalidDomains()) 
					setError(url, "Not same domain : ("+profile.getBaseDomain()+") " + url.toString(), true);
				if (profile.getMethod() == Constants.METHOD.DEPTH_FIRST)
					URLToVisitInDepth.remove(new VisitedURL(url.toString()));
				return false;
			case SAME: break;
			case ERROR: 
			default:
				if (profile.getVerbose() && profile.getShowSkippedURL()) setError(url, "Skipping URL : " + url + " - invalid host", true);
				if (profile.getMethod() == Constants.METHOD.DEPTH_FIRST) 
					URLToVisitInDepth.remove(new VisitedURL(url.toString()));
				return false; 
			}
		}
		
		// check url depth
		if (depth > profile.getMaxDepth()) {
			if (profile.getVerbose() && profile.getShowSkippedForDepthURL()) setError(url, "url too depth : " + url, true);
			return false;
		}
		return true;
	}

	// trap spider https://forums.asp.net/login.aspx?ReturnUrl=%2fuser%2fcreateuser.aspx
	/*
	try {
		URLToVisit.add(new URL("https://forums.asp.net/login.aspx?ReturnUrl=%252525252fuser%252525252fcreateuser.aspx"));
	} catch (MalformedURLException e) {
		e.printStackTrace();
	}
	*/
	/*
	 * Differents spider trap :
	 * Un lien vide <a></a> : ignorer
	 * Décoder les urls avant de les renvoyer
	 * 
	 */
	
	public void setProfile(Profile profile, String url) { 
		if (url != null) profile.setStartSite(url);
		this.profile = profile; 
	}
	public Profile getParameters() { return profile; }
	public Vector<URL> getURLToVisit() { return URLToVisit; }
	public ColoredTee getTee() { return tee; }
	
	private String format(String string) { return string != null ? string.replaceAll("\n", "").replaceAll("\\s+", " ").trim() : ""; } 
	private String host(URL url) { return url.getProtocol() + "://" + url.getHost(); }
	private String file(URL url) { return url.getProtocol() + "://" + url.getHost() + url.getFile(); }	
	private int depth (URL url) { 
		String path = url.getPath();
		return path.equals("/") ? 0 : path.indexOf("/") == -1 ? 0 : (path.substring(1)).split("/").length - 1; 
	}
	
	private void setError(VisitedURL url, String message, boolean ln) {
		url.setError(true);
		tee.writeError(message, ln);
	}
	
	private void showStart() {
		tee.writeMessage("\nSpider starts a crawl from : "+ profile.getStartSite() + " at " + (startTime = new Date()) + "\n", true);
	}
	
	private void showEnd() {
		// get endTime and substact startTime to compute elapsed time
        endTime = new Date();
        long diff = endTime.getTime() - startTime.getTime();
        int days = (int) (diff / (1000 * 60 * 60 * 24));
        diff -= days * (1000 * 60 * 60 * 24);
        int hours = (int) (diff / (1000 * 60 * 60));
        diff -= hours * (1000 * 60 * 60);
        int min = (int) (diff / (1000 * 60));
        diff -= min * (1000 * 60);
        int sec = (int) diff / 1000;
        diff -= sec * 1000;
        long ms = diff;
        
    	String message = "Job start at " + endTime + " - Job "+(interrupted ? "interrupted" : "done")+" at " + startTime + " - Total running time : " 
			+ (days != 0 ? days + " days ": "") 
    		+ (hours != 0 ? hours + "h ": "") 
    		+ (min != 0 ? min + "m ": "") 
    		+ (sec != 0 ? sec + "s ": "") 
    		+ (ms >= 0 ? ms + "ms ": "");
    	tee.writeMessage("\n"+message, true);
    	tee.showEnd(interrupted, statistics, message);
	}
	
	/**
	 * Thread control subsystem
	 * 
	 */
	// http://java.sun.com/j2se/1.5.0/docs/guide/misc/threadPrimitiveDeprecation.html
	// Spider's thread is runned using synchronization on two object properties 
	// to avoid deadlock in response to Thread.stop and Thread.resume deprecation
	// the two properties are :
	// - blinker is a reference to initial thread
	// - suspended allow thread to switch between sleep and running state 
	public synchronized Status stopSearch() {
		blinker = null; 
		suspended = true; 
		interrupted = true;
		notify();
		return Status.STOPPED;
	}
	public synchronized Status switchSuspended() { 
		if (!(suspended = !suspended)) notify(); 
		return suspended ? Status.PAUSED : Status.RUNNING; 
	} 
	private void checkSuspended() {
        if (suspended)
            synchronized(this) {
                while (suspended && blinker == Thread.currentThread())
					try {
						wait(); // wait until awaken by next switchSuspended() call or until thread death
					} catch (InterruptedException e) {}
            }
	}
	public synchronized Status getStatus() {
		if (blinker == null) return Status.STOPPED;
		if (suspended) return Status.PAUSED;
		return Status.RUNNING;
	}
	
	public void forcePause() { 
		this.suspended = true; 
	}
	
	/**
	 * internal trees iterator
	 */
	public Iterator<Entry<VisitedURL, String>> getVisitedURLIterator() {
		return visitedURL.entrySet().iterator();
	}
	
	public Iterator<URL> getURLToVisitIterator() {
		return URLToVisit.iterator();
	}
	
}
