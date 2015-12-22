package spiders.components.listeners;

import java.awt.event.ItemEvent;
import java.util.Vector;

import javax.swing.JComboBox;

import spiders.SpiderView;
import spiders.datas.Profile;

public class ProfileListener implements java.awt.event.ItemListener {
	
	
	private SpiderView view;
	private Vector<Profile> profiles;
	
	public ProfileListener(SpiderView view, Vector<Profile> profiles) {
		this.view = view;
		this.profiles = profiles;
	}
	
	public void itemStateChanged(java.awt.event.ItemEvent e) {
		if (!(e.getSource() instanceof JComboBox)) return; 
		JComboBox<?> source = (JComboBox<?>) e.getSource();
		if (e.getStateChange() == ItemEvent.SELECTED) {
			// search for unique site in startSite list
			String url = source.getSelectedItem().toString();
			Boolean exists = false;
			Boolean twice = false;
    		for (int i = 0; i < source.getItemCount(); i++) 
	    		if (url.equalsIgnoreCase(source.getItemAt(i).toString())) {
    				if (exists) twice = true;
    				else exists = true;	
    				break;
    			}
			if (exists)
				if (twice) 
					view.clearSubDomains();
				else {
					// search for correpondong profile
					if (profiles != null) for (Profile profile : profiles) {
						if (url.equals(profile.getStartSite().toString())) {
							view.setSubDomains(profile);
							break;
						}
					}
				}
		}					
	}
}
