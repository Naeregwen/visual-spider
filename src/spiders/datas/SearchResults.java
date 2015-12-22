package spiders.datas;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import spiders.SpiderView;
import spiders.constants.Constants;
import spiders.constants.Xml;
import spiders.parsers.ParametersParser;
import spiders.parsers.ParametersParser.ParsingMethod;

public class SearchResults extends Profile {

	private SpiderView view;
	private Vector<VisitedURL> visitedURL;
	private Vector<URL> URLToVisit;
	
	public SearchResults(SpiderView view) {
		this.view = view;
		visitedURL = new Vector<VisitedURL>();
		URLToVisit = new Vector<URL>();
	}
	
	public SearchResults(SpiderView view, Profile profile, Vector<URL> URLToVisit) {
		super(profile);
		this.view = view;
		visitedURL = new Vector<VisitedURL>();
		this.URLToVisit = URLToVisit;
	}

	public Vector<VisitedURL> getVisitedURL() { return visitedURL; }
	public Vector<URL> getURLToVisit() { return URLToVisit; }
	
	public boolean write(File file) {

		try {
			// open stream
			Writer writer = new BufferedWriter(new OutputStreamWriter(
		            new FileOutputStream(file.getAbsoluteFile()), Constants.HOSTFS_ENCODING));
			
			//FileWriter writer = new FileWriter(file);
			writer.write(Xml.XMLTAGLN);
			writer.write(Xml.openTagln("search"));	

			// write search profile
			write(writer, Profile.Position.MIDDLE);

			// write search results tree
			writer.write("\t"+Xml.openTagln("results"));
			DefaultMutableTreeNode root = (DefaultMutableTreeNode)((DefaultTreeModel)view.getResultsTree().getModel()).getRoot();
			writeResultsTree(root.depthFirstEnumeration(), root, writer);
			writer.write("\t"+Xml.closeTagln("results"));

			// write URLToVisit
			Iterator<URL> URLIterator = URLToVisit.iterator();
			if (URLIterator.hasNext()) {
				writer.write("\t"+Xml.openTagln("URLToVisit"));
				while (URLIterator.hasNext()) 
					writer.write("\t\t"+Xml.openTag("URL")
							+URLEncoder.encode(URLIterator.next().toString(), Constants.HOSTFS_ENCODING)
							+Xml.closeTagln("URL"));
				writer.write("\t"+Xml.closeTagln("URLToVisit"));
			}

			// close stream
			writer.write(Xml.closeTagln("search"));
			writer.flush();
			writer.close();			

		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	private boolean writeResultsTree(Enumeration<?> e, DefaultMutableTreeNode root, Writer writer) {
		if (!e.hasMoreElements()) return true;
		DefaultMutableTreeNode currentNode = (DefaultMutableTreeNode) e.nextElement();
		if (currentNode == root) return true;
		VisitedURL url = (VisitedURL)(currentNode.getUserObject());
		try {
			writeResultsTree(e, root, writer);
			writer.write("\t\t"+Xml.openTag("node")
					+url.getnChars()+","
					+url.getnImages()+","
					+url.getnLinks()+","
					+URLEncoder.encode(url.toURL().toString(), Constants.HOSTFS_ENCODING)+","
					+url.getTitle()
					+Xml.closeTagln("node"));
		} catch (IOException e1) {
			e1.printStackTrace();
			return false;
		}
		return true;
	}

	@SuppressWarnings("unchecked")
	public static SearchResults loadFromFile (String filename, SpiderView view) {
	    ParametersParser parser = new ParametersParser(filename, ParsingMethod.RESULTS, view);
		return ((Vector<SearchResults>) parser.parse()).firstElement();
	}
	
	public void addVisitedURL(VisitedURL url, int urlIndex) {
		if (url.toURL() == null) return;
		try {
			url.setURL(URLDecoder.decode(url.toURL().toString(), Constants.HOSTFS_ENCODING));
			visitedURL.add(url);
			view.addURL(url);
			view.setStatus("Visited url : " + urlIndex);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
	
	public void addURLToVisit(String value) {
		try {
			URLToVisit.add(new URL(URLDecoder.decode(value, Constants.HOSTFS_ENCODING)));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
}
