package spiders.datas;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Vector;

public class VisitedURL implements Comparable<Object> {
	
    /** set if the node is a plain text and not url */
    private boolean isText = true;
    /** url to wrap */
	private URL url;
	/** page title when exists */
	private String title;
	/** number of characters in the page */
	private Integer nChars = 0;
	/** numbers of links found in the page */
	private Integer nLinks = 0;
	/** number of images found in the page */
	private Integer nImages = 0;
	/** set to true when parsing has error */
	private Boolean hasError = false;
	/** set to true when page has been read */
	private Boolean hasBeenRead = false;
    /** set to true if node matched search criteria */
    private boolean isMatch = false;
    /** list of keywords matched by this node */
    private Vector<String> keywords = new Vector<String>(3,2);
	
	public VisitedURL (String url) { 
		try {
			this.url = new URL(url);
	        isText = false;
		} catch (MalformedURLException e) {
			title = url;
	        isText = true;
		} 			
	}

	public VisitedURL(String url, String title, Integer nChars, Integer nImages, Integer nLinks) {
		this(url);
		this.title = title;
		this.nChars = nChars;
		this.nImages = nImages;
		this.nLinks = nLinks;
		hasBeenRead = true;
	}
	
	public void setURL(String url) {
		try {
			this.url = new URL(url);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}
	public String getTitle() { return title; }
	public void setTitle(String title) { this.title = title; }		
	public Integer getnChars() { return nChars; }
	public void addChars(int n) { nChars += n; }
	public Integer getnLinks() { return nLinks; }
	public void addLinks(int n) { nLinks += n; }
	public Integer getnImages() { return nImages; }
	public void addImages(int n) { nImages += n; }
	public void setError(Boolean status) { hasError = status; }
	public Boolean hasError() { return hasError; }
	public void setRead(Boolean status) { hasBeenRead = status; }
	public Boolean hasBeenRead() { return hasBeenRead; }		
	
	public String toString() { 
		return isText ? title : url.toString(); 
	}
	public URL toURL() { return isText ? null : url; }
	
	public int compareTo(Object visitedURL) {
		return this.toString().compareTo(((VisitedURL) visitedURL).toString());
	}
	
    public boolean isParentOf(URL url) {
        if(isText && !title.equals("Root")) {
        	try {
				url = new URL (title);
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
        	
        }
        if (this.url != null ) {
        	if (url.toString().startsWith(this.url.toString()))
        		return true;        	
        }
    	return false;
    }
    
    public boolean isChildOf(URL url) {
        if (this.url != null ) {
        	if (!this.url.equals(url) && this.url.toString().startsWith(url.toString()))
        		return true;        	
        }
    	return false;
    }
    
    /**
     * test for equality
     * 
     * @param urlstr string containing url to compare
     * @return true if it is the same page
     */
    public boolean equals(String urlstr) {
    	if(isText)
    		return title.equals(urlstr);
    	else
    		return urlstr.equals(url.getProtocol()+"://"+url.getHost()+url.getPath());
    }

    /**
     * return matching state of node
     * 
     * @return true if node matched search criteria
     */
    public boolean isMatch() {
    	return isMatch;
    }

    /**
     *
     * return whether or not this node contains a match for the spiders search criteria
     * 
     * @param keyword keyword found in web site.
     */
	public void setMatch(String keyword) {
		isMatch = true;
		if (!keywords.contains(keyword))
			keywords.add(keyword);
	}
 
    /**
	 * get the keywords found in this node
	 * 
	 * @return all keywords in this node as a single comma separated string
	 */
    public String getKeywords() {
		String s ="";
		if (keywords.size() > 0) {
			s += (String)keywords.get(0);
			for (int i = 1; i < keywords.size(); i++)
				s +=  ", "+(String)keywords.get(i);        
		}
		return s;      
    }
    
    /**
     * retrieves character, link, and image count as a displayable string
     * 
     * @return pages statistics
     */
    public String getNodeStats() {
    	String keywords = getKeywords();
    	return isText || url == null ?
			"Page has no statistics" :
			hasBeenRead ?
			url+"\nPage has "
				+ nChars + " total text characters" 
				+ (nImages != 0 ? ", " + nImages + " images" : "") 
				+ " and " + nLinks + " links." + (keywords.equals("") ? "" : "\nKeywords found : "+keywords):
			url+"\nPage has not been read";
    }

}

