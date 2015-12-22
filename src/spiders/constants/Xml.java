package spiders.constants;

public class Xml {

	public static final String XMLTAGLN = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n";
	
	public static String openTag(String name) { return "<"+name+">"; }
	public static String openTagln(String name) { return "<"+name+">\n"; }
	public static String closeTag(String name) { return "</"+name+">"; }
	public static String closeTagln(String name) { return "</"+name+">\n"; }
	public static String tag(String name, String value) { return openTag(name) + value + closeTag(name); }
	public static String tagln(String name, String value) { return openTag(name) + value + closeTag(name) + "\n"; }
}
