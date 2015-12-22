package spiders.components.renderers;
/*
 * UrlNodeRenderer.java
 *
 * Created on September 20, 2004, 11:14 AM
 */
import java.awt.Color;
import java.awt.Component;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;

import spiders.datas.VisitedURL;
/**
 * Custom tree node renderer.  If the url has a match, it will be drawn in blue
 * simple icons are used for all nodes. 
 *
 * @author  Mark Pendergast
 */
public class VisitedURLRenderer extends DefaultTreeCellRenderer {
   
    /**
	 * 
	 */
	private static final long serialVersionUID = -7390801760783655873L;
	/**
     * icon used to display on the search tree
     */    
    public static Icon iconNotRead= null;
    public static Icon iconRead= null;
    public static Icon iconError= null;
    /** Creates a new instance of UrlNodeRenderer */
    public VisitedURLRenderer() {
        
    	iconNotRead = new ImageIcon(getClass().getResource("/spiders/images/NodeBlack.gif")); 
        iconRead = new ImageIcon(getClass().getResource("/spiders/images/NodeWhite.gif")); 
        iconError = new ImageIcon(getClass().getResource("/spiders/images/NodeRed.gif")); 
    }
    /**
     * Sets the value of the current tree cell to value.
     * If selected is true, the cell will be drawn as if selected.
     * If expanded is true the node is currently expanded and if leaf is 
     * true the node represets a leaf and if hasFocus is true the node 
     * currently has focus. tree is the JTree the receiver is being 
     * configured for. Returns the Component that the renderer uses to draw the value. 
     *
     * Modified the TreeCellRender to match a specific nodes attributes. If
     * the node is a match, then blue is used for the text color.  Icon is customized
     *
     * @param tree the JTree being redrawn
     * @param value node in the tree
     * @param sel true if selected by the user
     * @param expanded true if path is expanded
     * @param leaf true if this node is a leaf
     * @param row row number (vertical position)
     * @param hasFocus true if it has the focus
     * @return the Component that the renderer uses to draw the value

     */   
    public Component getTreeCellRendererComponent(
                        JTree tree,
                        Object value,
                        boolean sel,
                        boolean expanded,
                        boolean leaf,
                        int row,
                        boolean hasFocus) {

        super.getTreeCellRendererComponent(
                        tree, value, sel,
                        expanded, leaf, row,
                        hasFocus);
        
        VisitedURL node = (VisitedURL)(((DefaultMutableTreeNode)value).getUserObject());
        // set color and custom icon
        if (node.hasError()) { 
            setForeground(Color.red);
            if (iconError != null) {
                setOpenIcon(iconError);
                setClosedIcon(iconError);
                setLeafIcon(iconError);
            }
    	} else if (node.hasBeenRead()) {
        	setForeground(Color.blue);
            if (iconRead != null) {
                setOpenIcon(iconRead);
                setClosedIcon(iconRead);
                setLeafIcon(iconRead);
            }
    	} else if (node.isMatch()) {
            setForeground(Color.green);
            if (iconRead != null) {
                setOpenIcon(iconRead);
                setClosedIcon(iconRead);
                setLeafIcon(iconRead);
            }
    	} else {
           setForeground(Color.black);
           if (iconNotRead != null) {
               setOpenIcon(iconNotRead);
               setClosedIcon(iconNotRead);
               setLeafIcon(iconNotRead);
           }
    	}

        return this;
    }

    
}
