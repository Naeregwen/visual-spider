package spiders.util;

import java.util.Comparator;

public class StringExt {

	public static final Comparator<String> IgnoreCaseComparator = new Comparator<String>() {
        public int compare(String o1, String o2) {
        	return (o1.toLowerCase()).compareTo(o2.toLowerCase());
        }};
}
