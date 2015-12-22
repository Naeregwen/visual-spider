package spiders.util;

import java.io.File;

import javax.swing.filechooser.FileFilter;

public class XmlFilter extends FileFilter {

	//Accept all directories and xml files.
	@Override
	public boolean accept(File f) {		
		if (f.isDirectory()) return true;		
		String extension = FileExt.getExtension(f);
		return extension != null && extension.equals(FileExt.xml) ? true : false;
	}

	//The description of this filter
	public String getDescription() {
		return "Only xml files";
	}

}