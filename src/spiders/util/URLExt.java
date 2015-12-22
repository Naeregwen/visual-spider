package spiders.util;

import java.net.URL;

public class URLExt {

	public enum DOMAIN { SAME, NOT_SAME, ERROR }

	final String regexurl = "";
	
	public static DOMAIN isSameDomain(URL url, String baseDomain) {
	
		String[] domain = url.getHost().split("\\.");
		// skip invalid host (html error)
		if (domain.length < 3) return DOMAIN.ERROR; 
		if (!baseDomain.equals(domain[domain.length - 2] + "." + domain[domain.length-1])) 
			return DOMAIN.NOT_SAME;
		return DOMAIN.SAME;
	}
	
	public static String getExtension(URL url) {
		String path = url.getPath();
		int lastDot = path.lastIndexOf(".");
		return lastDot < 0 || lastDot >= path.length() ? "" : path.substring(lastDot);
	}
	
    public static String complete(String parent, String child) {
        String url = child;
        String urlLowCase = child.toLowerCase();
        String currentURLPath = parent.substring(0, parent.lastIndexOf("/"));
        String rootURL = parent.substring(0, parent.indexOf("/", 8));

        if (urlLowCase.startsWith("/")) {
            url = rootURL + url;
        } else if (urlLowCase.startsWith("./")) {
            url = currentURLPath + url.substring(1, url.length());
        } else if (urlLowCase.startsWith("../")) {
            int back = 1;

            while (urlLowCase.indexOf("../", back * 3) != -1)
                back++;

            int pos = currentURLPath.length();
            int count = back;

            while (count-- > 0) {
                pos = currentURLPath.lastIndexOf("/", pos) - 1;
            }

            url = currentURLPath.substring(0, pos + 2) + url.substring(3 * back, url.length());
        } else if (urlLowCase.startsWith("javascript:")) {
            // handle javascript:...
            System.err.println(".parseHREF(): parseJavaScript is not implemented yet");
        } else if (urlLowCase.startsWith("#")) {
            // internal anchor... ignore.
            url = null;
        } else if (urlLowCase.startsWith("mailto:")) {
            // handle mailto:...
            url = null;
        } else {
            url = currentURLPath + "/" + url;
        }

        // strip anchor if exists otherwise crawler may index content multiple times
        // links to the same url but with unique anchors would be considered unique
        // by the crawler when they should not be
        if (url != null) {
            int i;
            if ((i = url.indexOf("#")) != -1) {
                url = url.substring(0, i);
            }
        }

        return url;
    } 
}
