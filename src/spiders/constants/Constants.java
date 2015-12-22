package spiders.constants;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Vector;

public class Constants {

	// configuration filename
	public static final String CONF = "conf.xml";
	// profiles filename
	public static final String PROF = "prof.xml";
	// Alternative configuration directory
	public static final String PATH = "spiders/config/";
	
	// encoding to use when reading/writing into host filesystem 
	public static final String HOSTFS_ENCODING = "UTF8";
	
	// available crawling method
	public enum METHOD { BREADTH_FIRST, DEPTH_FIRST }
	
	// predefined extensions group constants
	public enum EXTENSIONS_STYLE { FULL, STATIC, DYNAMIC }
	
	public static final Vector<URL> SUGGEST = new Vector<URL>();
	static {
		try {
			SUGGEST.add(new URL("http://www.baleineprod.com"));
			SUGGEST.add(new URL("http://www.free.fr"));
			SUGGEST.add(new URL("http://www.google.fr"));
			SUGGEST.add(new URL("http://www.voila.fr"));
			SUGGEST.add(new URL("http://www.wanadoo.fr"));
			SUGGEST.add(new URL("http://www.yahoo.fr"));		
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}
	
	public static final Vector<String> DEFAULT_FULL_EXTENSIONS = new Vector<String>();
	static {
		DEFAULT_FULL_EXTENSIONS.add(".asp");
		DEFAULT_FULL_EXTENSIONS.add(".aspx");
		DEFAULT_FULL_EXTENSIONS.add(".htm");
		DEFAULT_FULL_EXTENSIONS.add(".html");
		DEFAULT_FULL_EXTENSIONS.add(".jsp");
		DEFAULT_FULL_EXTENSIONS.add(".php");
	}
	public static final Vector<String> DEFAULT_STATIC_EXTENSIONS = new Vector<String>();
	static {
		DEFAULT_STATIC_EXTENSIONS.add(".htm");
		DEFAULT_STATIC_EXTENSIONS.add(".html");
	}
	public static final Vector<String> DEFAULT_DYNAMIC_EXTENSIONS = new Vector<String>();
	static {
		DEFAULT_DYNAMIC_EXTENSIONS.add(".asp");
		DEFAULT_DYNAMIC_EXTENSIONS.add(".aspx");
		DEFAULT_DYNAMIC_EXTENSIONS.add(".jsp");
		DEFAULT_DYNAMIC_EXTENSIONS.add(".php");
	}
	
    // message output formatting
	public enum FORMAT { DF, UDF, IDF } 
	
	// predefined crawling options constants
	public static final METHOD  DEFAULT_METHOD = METHOD.BREADTH_FIRST;
	public static final Integer DEFAULT_MAX_URL_TO_VISIT = 20;
	public static final Integer DEFAULT_MAX_DEPTH = 10;
	public static final Boolean DEFAULT_STAY_IN_DOMAIN = true;
	public static final Boolean DEFAULT_COUNT_IMAGES = true;
	
	// predefined verbosity constants
	public static final boolean DEFAULT_VERBOSE = true;
	public static final boolean DEFAULT_USE_CONSOLE = true;
	public static final boolean DEFAULT_SUMMARIZE_CRAWLING = true;
	public static final boolean DEFAULT_SHOW_INVALID_DOMAINS = false;
	public static final boolean DEFAULT_SHOW_INVALID_URL = false;
	public static final boolean DEFAULT_SHOW_VISITED_URL = false;
	public static final boolean DEFAULT_SHOW_SKIPPED_URL = false;
	public static final boolean DEFAULT_SHOW_SKIPPED_FOR_DEPTH_URL = false;
	public static final boolean DEFAULT_SHOW_ENCODING_PROBLEMS = false;
	public static final boolean DEFAULT_SHOW_ILLEGAL_ARGUMENTS = false;
	
	// predefined site size constants
	public enum SITESIZE { DEFAULT, SMALL, MIDDLE, BIG, HUGE, INFINITE }
	
	public static class SitesizeModel {
		
		private METHOD method;
		private Integer maxURLtoVisit;
		private Integer maxDepth;
		private Boolean stayInDomain;
		private Boolean countImages;
		
		public METHOD  getMethod() { return method; }
		public Integer getMaxURLtoVisit() { return maxURLtoVisit; }
		public Integer getMaxDepth() { return maxDepth; }
		public Boolean getStayInDomain() { return stayInDomain; }
		public Boolean getCountImages() { return countImages; }
		
		private static final METHOD DEFAULT_METHOD_DEFAULT_SITE = METHOD.DEPTH_FIRST;
		private static final int DEFAULT_MAXURL_DEFAULT_SITE = 10;
		private static final int DEFAULT_DEPTH_DEFAULT_SITE = 10;
		private static final boolean DEFAULT_STAY_DEFAULT_SITE = true;
		
		private static final METHOD DEFAULT_METHOD_SMALL_SITE = METHOD.BREADTH_FIRST;
		private static final int DEFAULT_MAXURL_SMALL_SITE = 100;
		private static final int DEFAULT_DEPTH_SMALL_SITE = 5;
		private static final boolean DEFAULT_STAY_SMALL_SITE = true;
		
		private static final METHOD DEFAULT_METHOD_MIDDLE_SITE = METHOD.BREADTH_FIRST;
		private static final int DEFAULT_MAXURL_MIDDLE_SITE = 1000;
		private static final int DEFAULT_DEPTH_MIDDLE_SITE = 10;
		private static final boolean DEFAULT_STAY_MIDDLE_SITE = true;
		
		private static final METHOD DEFAULT_METHOD_BIG_SITE = METHOD.DEPTH_FIRST;
		private static final int DEFAULT_MAXURL_BIG_SITE = 10000;
		private static final int DEFAULT_DEPTH_BIG_SITE = 100;
		private static final boolean DEFAULT_STAY_BIG_SITE = false;
		
		private static final METHOD DEFAULT_METHOD_HUGE_SITE = METHOD.DEPTH_FIRST;
		private static final int DEFAULT_MAXURL_HUGE_SITE = 100000;
		private static final int DEFAULT_DEPTH_HUGE_SITE = 100;
		private static final boolean DEFAULT_STAY_HUGE_SITE = false;
		
		private static final METHOD DEFAULT_METHOD_INFINITE_SITE = METHOD.DEPTH_FIRST;
		private static final int DEFAULT_MAXURL_INFINITE_SITE = 0;
		private static final int DEFAULT_DEPTH_INFINITE_SITE = 0;
		private static final boolean DEFAULT_STAY_INFINITE_SITE = false;

		public SitesizeModel(SITESIZE size) {
			
			switch (size) {
			case DEFAULT:
				method = DEFAULT_METHOD_DEFAULT_SITE;
				maxURLtoVisit = DEFAULT_MAXURL_DEFAULT_SITE;
				maxDepth = DEFAULT_DEPTH_DEFAULT_SITE;
				stayInDomain = DEFAULT_STAY_DEFAULT_SITE;
				countImages = DEFAULT_COUNT_IMAGES;
				break;
			case SMALL:
				method = DEFAULT_METHOD_SMALL_SITE;
				maxURLtoVisit = DEFAULT_MAXURL_SMALL_SITE;
				maxDepth = DEFAULT_DEPTH_SMALL_SITE;
				stayInDomain = DEFAULT_STAY_SMALL_SITE;
				countImages = DEFAULT_COUNT_IMAGES;
				break;
			case MIDDLE:
				method = DEFAULT_METHOD_MIDDLE_SITE;
				maxURLtoVisit = DEFAULT_MAXURL_MIDDLE_SITE;
				maxDepth = DEFAULT_DEPTH_MIDDLE_SITE;
				stayInDomain = DEFAULT_STAY_MIDDLE_SITE;
				countImages = DEFAULT_COUNT_IMAGES;
				break;
			case BIG:
				method = DEFAULT_METHOD_BIG_SITE;
				maxURLtoVisit = DEFAULT_MAXURL_BIG_SITE;
				maxDepth = DEFAULT_DEPTH_BIG_SITE;
				stayInDomain = DEFAULT_STAY_BIG_SITE;
				countImages = DEFAULT_COUNT_IMAGES;
				break;
			case HUGE:
				method = DEFAULT_METHOD_HUGE_SITE;
				maxURLtoVisit = DEFAULT_MAXURL_HUGE_SITE;
				maxDepth = DEFAULT_DEPTH_HUGE_SITE;
				stayInDomain = DEFAULT_STAY_HUGE_SITE;
				countImages = DEFAULT_COUNT_IMAGES;
				break;
			case INFINITE:
				method = DEFAULT_METHOD_INFINITE_SITE;
				maxURLtoVisit = DEFAULT_MAXURL_INFINITE_SITE;
				maxDepth = DEFAULT_DEPTH_INFINITE_SITE;
				stayInDomain = DEFAULT_STAY_INFINITE_SITE;
				countImages = DEFAULT_COUNT_IMAGES;
				break;
			}
		}
	}	
}
