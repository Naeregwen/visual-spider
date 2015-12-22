package spiders.parsers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Vector;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

import spiders.SpiderView;
import spiders.constants.Constants;
import spiders.datas.Profile;
import spiders.datas.SearchResults;
import spiders.datas.VisitedURL;

/**
 * Xml file parser for spider parameters
 * <br><br>Can parse three file structures:
 * <ol> 
 * <li>Configuration file, which contains a single profile
 * <li>Profile file, which contains a list of profiles
 * <li>Results file, which contains a profile followed by search datas (visitedURL & URLToVisit)
 * </ol>
 * 
 * @author alex
 *
 */
public class ParametersParser extends DefaultHandler {

	public enum NameFormat { LOWERCASE, FIRSTCAPITAL }
	public enum ParsingMethod { CONFIGURATION, PROFILES, RESULTS };
	
	private ParsingMethod method; // parsing method to use : configuration, profile or results
	private SpiderView view; // view associated to user output
	private File file; // file to use
	private FileInputStream stream; // stream associated to filename
	private Vector<? extends Profile> results; // results container 
	private int urlIndex; // index of url currently been parsed
	private String value; // characters container	
	Action action; // reference to action for each tag
	
	/**
	 * Constructor for configuration and profiles parsing
	 * 
	 * @param filename
	 * @param method
	 * @param tee
	 */
	public ParametersParser(String filename, ParsingMethod method, SpiderView view) {
		try {
			this.file = new File(filename);
			this.view = view;
			this.method = method;
			stream = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			view.getTee().writeError(getMethodName(NameFormat.FIRSTCAPITAL) + " file not found : " + filename, true);
		}
	}
	
	public Boolean FileExists() { return stream != null; }
	
	public ParsingMethod getMethod() { 
		return method;
	}
	
	public static String getMethodName(ParsingMethod method, NameFormat format) { 
		String name =  method == ParsingMethod.CONFIGURATION ? 
				"Configuration" : method == ParsingMethod.PROFILES ? "Profiling" : "Results loading";
		return format == NameFormat.LOWERCASE ? name.toLowerCase() : name; 
	}
	
	public String getMethodName(NameFormat format) { 
		return getMethodName(method, format);
	}
	
	
	@SuppressWarnings("unchecked")
	private void addResult() {
		if (method == ParsingMethod.RESULTS)
			((Vector<SearchResults>)results).add(new SearchResults(view));
		else
			((Vector<Profile>)results).add(new Profile());
	}
	
	public Vector<? extends Profile> parse () {
		
		urlIndex = 0;
		results = method == ParsingMethod.RESULTS ? new Vector<SearchResults>() : new Vector<Profile>();
		if (stream != null) try {		
			XMLReader xr = XMLReaderFactory.createXMLReader();
			xr.setContentHandler(this);
			xr.setErrorHandler(this);
			view.getTee().writeInfos(getMethodName(NameFormat.FIRSTCAPITAL)+" is using file : "
	    			+file.getAbsolutePath(), true);
		    xr.parse(new InputSource(stream));		    
		} catch (SAXException se) {
			view.getTee().writeError(getMethodName(NameFormat.FIRSTCAPITAL) + " SAXException in file '" 
					+ file.getAbsolutePath() + "' : " + se.getLocalizedMessage(), true);
		} catch (IOException ie) {
			view.getTee().writeError(getMethodName(NameFormat.FIRSTCAPITAL) + " IOException in file '" 
					+ file.getAbsolutePath() + "' : " + ie.getLocalizedMessage(), true);
		}
		//if (results.size() == 0) addResult();
		return results;
	}

	// static map for dynamic parsing
	private static class Action {
		String name = "";
		@SuppressWarnings("rawtypes")
		Class[] classes = new Class[] {};
		Action (String name, @SuppressWarnings("rawtypes") Class[] classes) {
			this.name = name;
			this.classes = classes;
		}
		String getName() { return name; }
		@SuppressWarnings("rawtypes")
		Class[] getClasses() { return classes; }
	}
	
	private static final HashMap<String, Action> ACTIONS = new HashMap<String, Action>();
	static {
		ACTIONS.put("name", new Action("setName", new Class[] {String.class}));
		ACTIONS.put("startSite", new Action("setStartSite", new Class[] {String.class}));
		ACTIONS.put("excludedURL", new Action("addExcludedURL", new Class[] {String.class}));
		ACTIONS.put("includedURL", new Action("addIncludedURL", new Class[] {String.class}));
		ACTIONS.put("allowedExtension", new Action("addExtension", new Class[] {String.class}));
		ACTIONS.put("method", new Action("setMethod", new Class[] {Constants.METHOD.class}));
		ACTIONS.put("maxURLToVisit", new Action("setMaxURLtoVisit", new Class[] {Integer.class}));
		ACTIONS.put("maxDepth", new Action("setMaxDepth", new Class[] {Integer.class}));
		ACTIONS.put("stayInDomain", new Action("setStayInDomain", new Class[] {Boolean.class}));
		ACTIONS.put("countImages", new Action("setCountImages", new Class[] {Boolean.class}));
		ACTIONS.put("verbose", new Action("setVerbose", new Class[] {Boolean.class}));
		ACTIONS.put("useConsole", new Action("setUseConsole", new Class[] {Boolean.class}));
		ACTIONS.put("summarizeCrawling", new Action("setSummarizeCrawling", new Class[] {Boolean.class}));
		ACTIONS.put("showInvalidDomains", new Action("setShowInvalidDomains", new Class[] {Boolean.class}));
		ACTIONS.put("showInvalidURL", new Action("setShowInvalidURL", new Class[] {Boolean.class}));
		ACTIONS.put("showVisitedURL", new Action("setShowVisitedURL", new Class[] {Boolean.class}));
		ACTIONS.put("showSkippedURL", new Action("setShowSkippedURL", new Class[] {Boolean.class}));
		ACTIONS.put("showSkippedForDepthURL", new Action("setShowSkippedForDepthURL", new Class[] {Boolean.class}));
		ACTIONS.put("showEncodingProblems", new Action("setShowEncodingProblems", new Class[] {Boolean.class}));
		ACTIONS.put("showIllegalArguments", new Action("setShowIllegalArguments", new Class[] {Boolean.class}));
	}
	
	private static final Vector<String> ALLOWED_TAG_CONFIGURATION = new Vector<String>();
	static {
		ALLOWED_TAG_CONFIGURATION.add("parameters");
		ALLOWED_TAG_CONFIGURATION.add("allowedExtensions");
	}
	
	private static final Vector<String> ALLOWED_TAG_PROFILES = new Vector<String>();
	static {
		ALLOWED_TAG_PROFILES.add("profiles");
		ALLOWED_TAG_PROFILES.add("excludedURLs");
		ALLOWED_TAG_PROFILES.add("includedURLs");
		ALLOWED_TAG_PROFILES.add("allowedExtensions");
	}
	
	private static final Vector<String> ALLOWED_TAG_RESULTS = new Vector<String>();
	static {
		ALLOWED_TAG_RESULTS.add("search");
		ALLOWED_TAG_RESULTS.add("excludedURLs");
		ALLOWED_TAG_RESULTS.add("includedURLs");
		ALLOWED_TAG_RESULTS.add("allowedExtensions");
		ALLOWED_TAG_RESULTS.add("results");
		ALLOWED_TAG_RESULTS.add("URLToVisit");
	}
	
	// Event Handlers
	/**
	 * Called event when a start tag has been parsed
	 * 
	 * clear character container
	 * add a new profile when parsed tag is profile
	 */
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {		
		value = ""; // reset characters receiver
		if ((method == ParsingMethod.CONFIGURATION && qName.equalsIgnoreCase("parameters")) 
				|| (method == ParsingMethod.PROFILES && qName.equalsIgnoreCase("profile"))
				|| (method == ParsingMethod.RESULTS && results.size() == 0))
			addResult();
	}
	
	public void characters(char[] ch, int start, int length) throws SAXException {
		value = new String(ch,start,length);
	}
	
	@SuppressWarnings("unchecked")
	public void endElement(String uri, String localName, String qName) {

		if ((method == ParsingMethod.CONFIGURATION && qName.equalsIgnoreCase("parameters")) 
				|| qName.equalsIgnoreCase("profile")) return;
		
		// check for same profile name in profiles list
		if (qName.equalsIgnoreCase("name") && results.size() > 1) {
			for (Profile result : (Vector<Profile>)results) {
				if (result.getName() != null && result.getName().equals(value) 
						&& result != results.lastElement()) {
					view.getTee().writeError("Same profile name "+value, true);
					return;
				}
			}
		}
		
		// try to find profile setters associated to tag name
		try {
			if ((action = ACTIONS.get(qName)) == null) {
				Boolean found = false;
				switch(method) {
				case CONFIGURATION:
					for (String tag : ALLOWED_TAG_CONFIGURATION) 
						if (tag.equalsIgnoreCase(qName)) {
							found = true;
							break;
						}
					break;
				case PROFILES:
					for (String tag : ALLOWED_TAG_PROFILES) 
						if (tag.equalsIgnoreCase(qName)) {
							found = true;
							break;
						}					
					break;
				case RESULTS:
					if (qName.equalsIgnoreCase("node") ) {
						parseNode(qName);
						return;
					} else if (	qName.equalsIgnoreCase("url")) {
						readURL();
						return;
					} else
						for (String tag : ALLOWED_TAG_RESULTS) 
							if (tag.equalsIgnoreCase(qName)) {
								found = true;
								break;
							}					
					break;
				}
				if (!found) 
					view.getTee().writeError(getMethodName(NameFormat.FIRSTCAPITAL) + " unknown parameter : " + qName + " (ignoring this tag)", true);
				return;
			}
			
			// find method to apply for last results element related to action
			//Method method = ((Profile)(results.lastElement())).getClass().getDeclaredMethod(action.getName(), action.getClasses());			
			Method method = Profile.class.getDeclaredMethod(action.getName(), action.getClasses());			
			Object argument = new Object();
			boolean parseError = false;
			if (action.getClasses().length > 0)
				if (action.getClasses()[0] == String.class)
					argument = value;
				else if (action.getClasses()[0] == Constants.METHOD.class) {
					if ((argument = parseMethod()) != METHOD.ERROR)
						argument = ((METHOD)argument).toMethod();
					else {
						view.getTee().writeError(getMethodName(NameFormat.FIRSTCAPITAL) + " invalid named agument '"+value+"' for " + qName + " parameter", true);
						parseError = true;
					}
				} else if (action.getClasses()[0] == Boolean.class) {
					if ((argument = parseBoolean()) != BOOLEAN.ERROR)
						argument = ((BOOLEAN) argument).toBoolean();						
					else {
						view.getTee().writeError(getMethodName(NameFormat.FIRSTCAPITAL) + " invalid boolean agument '"+value+"' for " + qName + " parameter", true);
						parseError = true;
					}
				} else if (action.getClasses()[0] == Integer.class)
					try {
						argument = Integer.parseInt(value);
					} catch (NumberFormatException nfe) {
						view.getTee().writeError(getMethodName(NameFormat.FIRSTCAPITAL) + " invalid numeric agument '"+value+"' for " + qName + " parameter", true);
						parseError = true;
					}
			// apply action to last results element
			if (method != null && !parseError) method.invoke(results.lastElement(), new Object[] {argument} );
			
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			view.getTee().writeError(getMethodName(NameFormat.FIRSTCAPITAL) + " invalid agument '"+value+"' for " + qName + " parameter", true);
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}
	
	// structured line parsing method
	private void parseNode(String qName) {
		String url = null;
		StringBuilder title = new StringBuilder();
		Integer nChars = 0, nImages = 0, nLinks = 0;
		String[] tokens = value.split(",");
		for (int i = 0; i < tokens.length; i++) {
			String token = tokens[i].trim();
			try {
				switch (i) {
				case 0 : nChars = Integer.parseInt(token); break;
				case 1 : nImages = Integer.parseInt(token); break;
				case 2 : nLinks = Integer.parseInt(token); break;
				case 3 : url = token; break;
				default: title.append(token); break;
				}
			} catch (NumberFormatException nfe) {
				view.getTee().writeError(getMethodName(NameFormat.FIRSTCAPITAL) + " invalid numeric agument '"+value+"' for " + qName + " parameter", true);				
			}			
		}
		try {
			((SearchResults)(results.firstElement())).addVisitedURL(
					new VisitedURL(URLDecoder.decode(url, Constants.HOSTFS_ENCODING), title.toString(), nChars, nImages, nLinks), urlIndex++);
		} catch (UnsupportedEncodingException e) {
			view.getTee().writeError(getMethodName(NameFormat.FIRSTCAPITAL) + " invalid url agument '"+value+"' for " + qName + " parameter", true);				
		}
	}
	
	private void readURL() {
		((SearchResults)(results.firstElement())).addURLToVisit(value);
	}
	
	// non numeric lexem parsing methods
	private enum BOOLEAN { 
		FALSE, TRUE, ERROR; 
		Boolean toBoolean() { return this == FALSE ? false : this == TRUE ? true : false; }
	}
	
	private BOOLEAN parseBoolean() {
		boolean isFalse = value.equalsIgnoreCase("false") || value.equals("0");
		return (value.equalsIgnoreCase("true") || value.equals("1") || isFalse) ? 
			isFalse ? BOOLEAN.FALSE : BOOLEAN.TRUE : BOOLEAN.ERROR;
	}
	
	private enum METHOD { 
		BREADTH_FIRST, DEPTH_FIRST, ERROR; 
		Constants.METHOD toMethod() { 
			return 
				this == ParametersParser.METHOD.BREADTH_FIRST ? Constants.METHOD.BREADTH_FIRST : 
				this == ParametersParser.METHOD.DEPTH_FIRST ? Constants.METHOD.DEPTH_FIRST : Constants.METHOD.BREADTH_FIRST; 
		}
	}
	
	private ParametersParser.METHOD parseMethod() {
		boolean isDepth = value.equalsIgnoreCase("depth_first") || value.equalsIgnoreCase("depth first") || value.equalsIgnoreCase("depth");
		boolean isBreadth = value.equalsIgnoreCase("breadth_first") || value.equalsIgnoreCase("breadth first") || value.equalsIgnoreCase("breadth");		
		return (isDepth || isBreadth) ? isDepth ? METHOD.DEPTH_FIRST : METHOD.BREADTH_FIRST : METHOD.ERROR;
	}

}
