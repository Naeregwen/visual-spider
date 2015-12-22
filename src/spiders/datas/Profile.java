package spiders.datas;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.Collections;
import java.util.Vector;

import spiders.SpiderView;
import spiders.components.ColoredTee;
import spiders.constants.Constants;
import spiders.constants.Xml;
import spiders.constants.Constants.METHOD;
import spiders.parsers.ParametersParser;
import spiders.parsers.ParametersParser.NameFormat;
import spiders.parsers.ParametersParser.ParsingMethod;

public class Profile {
	
	// user name for this profile
	private String name;
	// define start url site to crawl
	private URL startSite;
	// base domain when limited crawling
	private String baseDomain;
	
	// excluded subdomains
	private Vector<String> excludedURL = new Vector<String>();
	// included subdomains
	private Vector<String> includedURL = new Vector<String>();

	// allowed file extensions
	private Vector<String> allowedExtensions = new Vector<String>();
		
	// crawling options
	private METHOD  method = Constants.DEFAULT_METHOD;
	private Integer maxURLtoVisit = Constants.DEFAULT_MAX_URL_TO_VISIT;
	private Integer maxDepth = Constants.DEFAULT_MAX_DEPTH; // Max depth of URL to crawl
	private Boolean stayInDomain = Constants.DEFAULT_STAY_IN_DOMAIN; // keep crawling in same domain 
	private Boolean countImages = Constants.DEFAULT_COUNT_IMAGES; // get images count
	
	// verbosity options
	private Boolean verbose = Constants.DEFAULT_VERBOSE;
	private Boolean useConsole = Constants.DEFAULT_USE_CONSOLE;
	private Boolean summarizeCrawling = Constants.DEFAULT_SUMMARIZE_CRAWLING;
	private Boolean showInvalidDomains = Constants.DEFAULT_SHOW_INVALID_DOMAINS;
	private Boolean showInvalidURL = Constants.DEFAULT_SHOW_INVALID_URL;
	private Boolean showVisitedURL = Constants.DEFAULT_SHOW_VISITED_URL;
	private Boolean showSkippedURL = Constants.DEFAULT_SHOW_SKIPPED_URL;
	private Boolean showSkippedForDepthURL = Constants.DEFAULT_SHOW_SKIPPED_FOR_DEPTH_URL;
	private Boolean showEncodingProblems = Constants.DEFAULT_SHOW_ENCODING_PROBLEMS;
	private Boolean showIllegalArguments = Constants.DEFAULT_SHOW_ILLEGAL_ARGUMENTS;
	
    // message output formatting
	private DecimalFormat df, udf, idf;
	
	// 
	public enum Selection { CURRENT_PROFILE, ALL_PROFILES, CURRENT_SEARCH }
	public enum Position { FIRST, MIDDLE, LAST, ONE }
		
	public Profile() {
		setDefaultParameters();
	}

	public Profile(String url) {
		setStartSite(url);
		setDefaultParameters();
	}
	
	public Profile(Profile profile) {
		clone(profile);
	}

	// profile identity
	public String getName() { return name; }
	// site parameters
	public URL getStartSite() { return startSite; }
	public String getBaseDomain() { return baseDomain; }
	public Vector<String> getIncludedURL() { return includedURL; }
	public Vector<String> getExcludedURL() { return excludedURL; }
	public Vector<String> getAllowedExtensions() { return allowedExtensions; }
	// crawling options
	public METHOD  getMethod() { return method; }
	public Integer getMaxURLtoVisit() { return maxURLtoVisit; }
	public Integer getMaxDepth() { return maxDepth; }
	public boolean getStayInDomain() { return stayInDomain; }
	public boolean getImagesCount() { return countImages; }
	// verbosity options
	public boolean getVerbose() { return verbose; }
	public boolean getUseConsole() { return useConsole; }
	public boolean getSummarizeCrawling() { return summarizeCrawling; }
	public boolean getShowInvalidDomains() { return showInvalidDomains; }
	public boolean getShowInvalidURL() { return showInvalidURL; }
	public boolean getShowVisitedURL() { return showVisitedURL; }
	public boolean getShowSkippedURL() { return showSkippedURL; }
	public boolean getShowSkippedForDepthURL() { return showSkippedForDepthURL; }
	public boolean getShowEncodingProblems() { return showEncodingProblems; }
	public boolean getShowIllegalArguments() { return showIllegalArguments; }

	// profile identity
	public void setName(String name) { this.name = name; }
	
	// site parameters
	public boolean setStartSite(String url) {
		if (url == null || url.equals("")) return false;
		String URLEnd = url.endsWith("/") ? "" : "/"; // to avoid reading two time same first url
		try {
			startSite = url.startsWith("www.") ? new URL("http://"+url+URLEnd) : new URL(url+URLEnd);
			setBaseDomain();
		} catch (MalformedURLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	private void setBaseDomain() {
		// build base domain string
		String[] domain = startSite.getHost().split("\\.");
		baseDomain = domain[domain.length - 2] + "." + domain[domain.length-1];
	}
	public void setBaseDomain(String baseDomain) { this.baseDomain = baseDomain; }
	public void setIncludedURL(Vector<String> includedURL) { this.includedURL = includedURL; }
	public void setExcludedURL(Vector<String> excludedURL) { this.excludedURL = excludedURL; }
	public void setAllowedExtensions(Vector<String> allowedExtensions) { this.allowedExtensions = allowedExtensions; }
	
	// crawling options
	public void setMethod(METHOD method) { this.method = method; }
	public void setMaxURLtoVisit(Integer maxURLtoVisit) { this.maxURLtoVisit = maxURLtoVisit; }
	public void setMaxDepth(Integer maxDepth) { this.maxDepth = maxDepth; }
	public void setStayInDomain(Boolean stayInDomain) { this.stayInDomain = stayInDomain; }
	public void setCountImages(Boolean countImages) { this.countImages = countImages; }
	
	// verbosity options
	public void setVerbose(Boolean verbose) { this.verbose = verbose; }
	public void setUseConsole(Boolean useConsole) { this.useConsole = useConsole; }
	public void setSummarizeCrawling(Boolean summarizeCrawling) { this.summarizeCrawling = summarizeCrawling; }
	public void setShowInvalidDomains(Boolean showInvalidDomains) { this.showInvalidDomains = showInvalidDomains; }
	public void setShowInvalidURL(Boolean showInvalidURL) { this.showInvalidURL = showInvalidURL; }
	public void setShowVisitedURL(Boolean showVisitedURL) { this.showVisitedURL = showVisitedURL; }
	public void setShowSkippedURL(Boolean showSkippedURL) { this.showSkippedURL = showSkippedURL; }
	public void setShowSkippedForDepthURL(Boolean showSkippedForDepthURL) { this.showSkippedForDepthURL = showSkippedForDepthURL; }
	public void setShowEncodingProblems(Boolean showEncodingProblems) { this.showEncodingProblems = showEncodingProblems; }
	public void setShowIllegalArguments(Boolean showIllegalArguments) { this.showIllegalArguments = showIllegalArguments; }
	
	public void addIncludedURL(String url) { 
		if (includedURL == null)
			includedURL = new Vector<String>(); 
		includedURL.add(url);
	}
	
	public void addExcludedURL(String url) { 
		if (excludedURL == null)
			excludedURL = new Vector<String>(); 
		excludedURL.add(url);
	}
	
	public void addExtension(String extension) { 
		if (allowedExtensions == null)
			allowedExtensions = new Vector<String>(); 
		allowedExtensions.add(extension);
	}
	
	private void setDefaultParameters() {
		
		// crawling parameters
		excludedURL = new Vector<String>();
		includedURL = new Vector<String>();
		allowedExtensions = new Vector<String>();
		
		// crawling options
		method = Constants.DEFAULT_METHOD;
		maxURLtoVisit = Constants.DEFAULT_MAX_URL_TO_VISIT;
		maxDepth = Constants.DEFAULT_MAX_DEPTH; // Max depth of URL to crawl
		stayInDomain = Constants.DEFAULT_STAY_IN_DOMAIN; // keep crawling in same domain 
		countImages = Constants.DEFAULT_COUNT_IMAGES; // get images count
		
		// build format string for visited URL index
		df = new DecimalFormat(setDecimalFormat(maxURLtoVisit));
	}
	
	public void clone(Profile profile) {
		
		// identity
		name = profile.getName();
		startSite = profile.getStartSite();
		setBaseDomain();
		
		// URL and extensions
		excludedURL = profile.getExcludedURL();
		includedURL = profile.getIncludedURL();
		allowedExtensions = profile.getAllowedExtensions();
		
		// crawling options
		method = profile.getMethod();
		maxURLtoVisit = profile.getMaxURLtoVisit();
		maxDepth = profile.getMaxDepth();
		stayInDomain = profile.getStayInDomain(); 
		countImages = profile.getImagesCount();
		
		df = new DecimalFormat(setDecimalFormat(maxURLtoVisit));
		
		// Verbosity
		verbose = profile.getVerbose();
		useConsole = profile.getUseConsole();
		summarizeCrawling = profile.getSummarizeCrawling();
		showInvalidDomains = profile.getShowInvalidDomains();
		showInvalidURL = profile.getShowInvalidURL();
		showVisitedURL = profile.getShowVisitedURL();
		showSkippedURL = profile.getShowSkippedURL();
		showSkippedForDepthURL = profile.getShowSkippedForDepthURL();
		showEncodingProblems = profile.getShowEncodingProblems();
		showIllegalArguments = profile.getShowIllegalArguments();
	}
	
	public String setDecimalFormat(Integer maxValue) {
		if (maxValue <= 0) return "0";
		StringBuffer format = new StringBuffer();
		for (int i = 0; i < maxValue.toString().length(); i++) format.append("0"); 
		return format.toString();
	} 

	public String format(Constants.FORMAT format, int size) {
		return format == Constants.FORMAT.UDF ? udf.format(size) : 
			format == Constants.FORMAT.IDF ? idf.format(size) : df.format(size);
	}
	
	public void setURLDecimalFormat(Integer maxValue) { udf = new DecimalFormat(setDecimalFormat(maxValue)); }
	public void setIMGDecimalFormat(Integer maxValue) { idf = new DecimalFormat(setDecimalFormat(maxValue)); }
	
	@SuppressWarnings("unchecked")
	public static Vector<Profile> loadFromFile(String filename, ParsingMethod method, SpiderView view) {
	    Vector<Profile> parameters = null;
	    ParametersParser parser = null;
	    if ((parameters = (Vector<Profile>) (parser = new ParametersParser(filename, method, view)).parse()) == null
	    		&& (parameters = (Vector<Profile>) (parser = new ParametersParser(Constants.PATH+filename, method, view)).parse()) == null)
	    	view.getTee().writeError(parser.getMethodName(NameFormat.FIRSTCAPITAL)+" does not found a valid file, no "
	    			+parser.getMethodName(NameFormat.LOWERCASE)+" loaded.", true);
	    else if (!isValid(parameters, parser, filename, view))
	    	return null;
	    return parameters;
	}

	public boolean writeToFile(File file, ColoredTee tee) {
		
		try {
			
			FileWriter writer = new FileWriter(file);
			write(writer, Position.ONE);
			writer.flush();
			writer.close();			
    		tee.writeInfos(ParametersParser
    				.getMethodName(ParametersParser.ParsingMethod.PROFILES, 
    						ParametersParser.NameFormat.FIRSTCAPITAL)
    						+" has been written to "
    						+file.getAbsoluteFile()+" file.", true);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public static boolean writeToFile (Vector<Profile> profiles, File file, ColoredTee tee) {
		
		int position = 0;
		try {
			
			FileWriter writer = new FileWriter(file);
			for (Profile profile : profiles) {
				if (!profile.write(writer, position == 0 ? 
						Profile.Position.FIRST : 
						position == profiles.size() - 1 ? 
								Profile.Position.LAST : 
								Profile.Position.MIDDLE))
					break;
				position++;
			}
			writer.flush();
			writer.close();			
			if (position != profiles.size()) return false;
			
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		tee.writeInfos(ParametersParser
				.getMethodName(ParametersParser.ParsingMethod.PROFILES, 
						ParametersParser.NameFormat.FIRSTCAPITAL)
						+" has been written to "
						+file.getAbsoluteFile()+" file.", true);
		return true;		
	}
	
	public boolean write(Writer writer, Position position) {
		
		try {
			
			if (position == Position.FIRST || position == Position.ONE) {
				writer.write(Xml.XMLTAGLN);
				writer.write(Xml.openTagln("profiles"));				
			}
			
			writer.write("\t"+Xml.openTagln("profile"));
			
			writer.write("\t\t"+Xml.tagln("name", name));
			writer.write("\t\t"+Xml.tagln("startSite", startSite.toString()));
			
			if (allowedExtensions.size() > 0) {
				writer.write("\t\t"+Xml.openTagln("allowedExtensions"));
				for (String extension : allowedExtensions) 
					writer.write("\t\t\t"+Xml.tagln("allowedExtension", extension));
				writer.write("\t\t"+Xml.closeTagln("allowedExtensions"));							
			}
			
			if (excludedURL.size() > 0) {
				writer.write("\t\t"+Xml.openTagln("excludedURLs"));
				for (String url : excludedURL) 
					writer.write("\t\t\t"+Xml.tagln("excludedURL", url));
				writer.write("\t\t"+Xml.closeTagln("excludedURLs"));							
			}
			
			if (includedURL.size() > 0) {
				writer.write("\t\t"+Xml.openTagln("includedURLs"));
				for (String url : includedURL) 
					writer.write("\t\t\t"+Xml.tagln("includedURL", url));
				writer.write("\t\t"+Xml.closeTagln("includedURLs"));							
			}
			
			writer.write("\t\t"+Xml.tagln("method", method.toString()));
			writer.write("\t\t"+Xml.tagln("maxURLToVisit", maxURLtoVisit.toString()));
			writer.write("\t\t"+Xml.tagln("maxDepth", maxDepth.toString()));
			writer.write("\t\t"+Xml.tagln("stayInDomain", stayInDomain.toString()));
			writer.write("\t\t"+Xml.tagln("countImages", countImages.toString()));
			writer.write("\t\t"+Xml.tagln("verbose", verbose.toString()));
			writer.write("\t\t"+Xml.tagln("useConsole", useConsole.toString()));
			writer.write("\t\t"+Xml.tagln("summarizeCrawling", summarizeCrawling.toString()));
			writer.write("\t\t"+Xml.tagln("showInvalidDomains", showInvalidDomains.toString()));
			writer.write("\t\t"+Xml.tagln("showInvalidURL", showInvalidURL.toString()));
			writer.write("\t\t"+Xml.tagln("showVisitedURL", showVisitedURL.toString()));
			writer.write("\t\t"+Xml.tagln("showSkippedURL", showSkippedURL.toString()));
			writer.write("\t\t"+Xml.tagln("showSkippedForDepthURL", showSkippedForDepthURL.toString()));
			writer.write("\t\t"+Xml.tagln("showEncodingProblems", showEncodingProblems.toString()));
			writer.write("\t\t"+Xml.tagln("showIllegalArguments", showIllegalArguments.toString()));
			
			writer.write("\t"+Xml.closeTagln("profile"));			

			if (position == Position.LAST || position == Position.ONE) 
				writer.write(Xml.closeTagln("profiles"));
				
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	private static Boolean isValid(Vector<Profile> parameters, ParametersParser parser, String filename, SpiderView view) {
		
		Vector<Profile> invalids = new Vector<Profile>();
		Integer position = 1;
		for (Profile parameter : parameters) 
			if (!isValid(parameter, parser, position++, filename, view)) 
				invalids.add(parameter);
		parameters.removeAll(invalids);		
		if (parameters.size() <= 0) 
			view.getTee().writeError("no valid external "+parser.getMethodName(NameFormat.LOWERCASE), true);
		return parameters.size() > 0 ? true : false;
	}
	
	private static Boolean isValid(Profile parameters, ParametersParser parser, Integer position, String filename, SpiderView view) {
		
		String methodName = parser.getMethodName(NameFormat.LOWERCASE);
		switch (parser.getMethod()) {
		case CONFIGURATION:			
			break;
		case PROFILES:
			String name = parameters.getName();
			if (name == null || name.trim().equals("")) {
				name = position.toString();
				view.getTee().writeError("Missing "+methodName+" name, "+methodName+" "+name+" ignored in file "+filename, true);
				return false;
			}
			if (parameters.startSite == null || parameters.startSite.toString().trim().equals("")) {
				if (name == null || name.trim().equals("")) name = "noname";
				view.getTee().writeError("Missing "+methodName+" startSite, "+methodName+" "+name+" ignored in file "+filename, true);
				return false;
			}			
			break;
		case RESULTS:
			
			break;
		}
		return true;
	}
	
	/**
	 * member equality for Profile class
	 * 
	 */ 
	public Boolean equals (Profile siteProfile) {
		
		if (!equals(excludedURL, siteProfile.excludedURL, false)
				|| !equals(includedURL, siteProfile.includedURL, false)
				|| !equals(allowedExtensions, siteProfile.allowedExtensions, true)
				|| method != siteProfile.method
				|| maxURLtoVisit != siteProfile.maxURLtoVisit
				|| maxDepth != siteProfile.maxDepth
				|| stayInDomain != siteProfile.stayInDomain
				|| countImages != siteProfile.countImages
				|| verbose != siteProfile.verbose
				|| useConsole != siteProfile.useConsole
				|| summarizeCrawling != siteProfile.summarizeCrawling
				|| showInvalidDomains != siteProfile.showInvalidDomains
				|| showInvalidURL != siteProfile.showInvalidURL
				|| showVisitedURL != siteProfile.showVisitedURL
				|| showSkippedURL != siteProfile.showSkippedURL
				|| showSkippedForDepthURL != siteProfile.showSkippedForDepthURL
				|| showEncodingProblems != siteProfile.showEncodingProblems
				|| showIllegalArguments != siteProfile.showIllegalArguments) 
			return false;
		return true;
	}
	
	/**
	 * equality for vector of string
	 * after reformatting and sorting of content string
	 */
	private Boolean equals (Vector<String> a, Vector<String> b, Boolean extension) {
		Vector<String> c = new Vector<String>(), d = new Vector<String>();
		String str;
		for (String string : a) {
			str = string.trim().toLowerCase();
			if (str.length() > 0)
				c.add(extension && str.length() > 1 && str.startsWith(".") ? 
						str.substring(1, str.length()) : str.toLowerCase());
		}
		for (String string : b) {
			str = string.trim().toLowerCase();
			if (str.length() > 0)
				d.add(extension && str.length() > 1 && str.startsWith(".") ? 
						str.substring(1, str.length()) : str.toLowerCase());
		}
		if (c.size() != d.size()) return false;
		Collections.sort(c);
		Collections.sort(d);
		for (int i = 0; i < c.size(); i++)
			if (!c.elementAt(i).equals(d.elementAt(i))) return false;
		return true;
	}
	
}
