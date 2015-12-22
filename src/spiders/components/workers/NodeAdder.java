package spiders.components.workers;

import java.net.URL;
import java.util.Enumeration;

import javax.swing.JTree;
import javax.swing.SwingWorker;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

import spiders.datas.VisitedURL;
import spiders.util.URLTool;

public class NodeAdder extends SwingWorker<DefaultMutableTreeNode, String> {

	private VisitedURL url;
	private JTree resultsTree;
	
	public NodeAdder(VisitedURL url, JTree resultsTree) {
		//super();
		this.url = url;
		this.resultsTree = resultsTree;
	}
	
	@Override
	protected DefaultMutableTreeNode doInBackground() throws Exception {
		return addNode();
	}
	
	private DefaultMutableTreeNode addNode() {
	
		// get model
		DefaultTreeModel treeModel = (DefaultTreeModel)resultsTree.getModel();
		// get parent url 
		URL parentPathURL = URLTool.getParent(url.toURL());
		String parentPath = parentPathURL == null ? url.toString() : parentPathURL.toString();
		// create new node
		DefaultMutableTreeNode node = new DefaultMutableTreeNode(url);
		// create parent node when not present in tree
		DefaultMutableTreeNode parent = getNode(parentPath);
		if (parent == (DefaultMutableTreeNode)treeModel.getRoot()) {
			VisitedURL parentURL = new VisitedURL(parentPath);
			parent = createNode(parentPath, "", getParent(parentURL.toURL()));
			// rebuild tree if child where build before parent node
			reassortTree(parent, parentURL.toURL(), treeModel);
		} 
		// attach node to parent or replace parent with node
		if (!url.equals(parentPath) && !(url+"/").equals(parentPath) )
			treeModel.insertNodeInto(node, parent, parent.getChildCount());
		else
			parent.setUserObject(node.getUserObject());
		// make sure the user can see the node just added
		updateTreeview(parent);
		return node;
	}

	private DefaultMutableTreeNode getNode(String url) {
		 
	   DefaultTreeModel treeModel = (DefaultTreeModel)resultsTree.getModel();
	   DefaultMutableTreeNode root = (DefaultMutableTreeNode)treeModel.getRoot();
	   Enumeration<?> etree = root.breadthFirstEnumeration();
	   while (etree.hasMoreElements()) {
		 DefaultMutableTreeNode treeNode = (DefaultMutableTreeNode) etree.nextElement();
		 if (treeNode.getUserObject() instanceof VisitedURL) {
			 VisitedURL node = (VisitedURL)(treeNode.getUserObject());
			 if (node.equals(url)) return treeNode;			 
		 }
	   }
	   return root;
	}

	private DefaultMutableTreeNode createNode(String url, String title, DefaultMutableTreeNode parent) {
		
		VisitedURL newURL = new VisitedURL(url); // create the node		  
		DefaultMutableTreeNode node = new DefaultMutableTreeNode(newURL);
		DefaultTreeModel treeModel = (DefaultTreeModel)resultsTree.getModel(); // get our model
		treeModel.insertNodeInto(node, parent, parent.getChildCount());
		return node;
	}
	
	private void reassortTree(DefaultMutableTreeNode parent, URL parentURL, DefaultTreeModel treeModel) {
		
		// backward ressort
		DefaultMutableTreeNode previousSibling = null;
		DefaultMutableTreeNode sibling = parent.getPreviousSibling();
		while (sibling != null) {
			VisitedURL siblingNode = (VisitedURL)(sibling.getUserObject());
			if (siblingNode.isChildOf(parentURL)){
				System.err.println("reassorting tree " + ((VisitedURL)(sibling.getUserObject())).toURL() + " is child of " + parentURL);	
				previousSibling = sibling.getPreviousSibling();
				treeModel.removeNodeFromParent(sibling);
				treeModel.insertNodeInto(sibling, parent, parent.getChildCount());
				updateTreeview(parent);
			}
			sibling = previousSibling != null ? previousSibling : sibling.getPreviousSibling();
			if (previousSibling != null) previousSibling = null;
		}
		// forward ressort
		DefaultMutableTreeNode nextSibling = null;
		sibling = parent.getNextSibling();
		while (sibling != null) {
			VisitedURL siblingNode = (VisitedURL)(sibling.getUserObject());
			if (siblingNode.isChildOf(parentURL)){
				System.err.println("reassorting tree " + ((VisitedURL)(sibling.getUserObject())).toURL() + " is child of " + parentURL);	
				nextSibling = sibling.getNextSibling();
				treeModel.removeNodeFromParent(sibling);
				treeModel.insertNodeInto(sibling, parent, parent.getChildCount());
				updateTreeview(parent);
			}
			sibling = nextSibling != null ? nextSibling : sibling.getNextSibling();
			if (nextSibling != null) nextSibling = null;
		}
		
	}
	
	private DefaultMutableTreeNode getParent(URL url) {
		 
		   DefaultTreeModel treeModel = (DefaultTreeModel)resultsTree.getModel();
		   DefaultMutableTreeNode root = (DefaultMutableTreeNode)treeModel.getRoot();
		   Enumeration<?> etree = root.breadthFirstEnumeration();
		   while (etree.hasMoreElements()) {
			 DefaultMutableTreeNode treeNode = (DefaultMutableTreeNode) etree.nextElement();
			 VisitedURL node = (VisitedURL)(treeNode.getUserObject());
		     if (node instanceof VisitedURL && node.isParentOf(url)) return treeNode;
		   }
		   return root;
		}
	
	/**
	 * visual update of a JTree to show a node
	 */
	private void updateTreeview(DefaultMutableTreeNode node) {
		
      TreePath tp = new TreePath(node.getPath());
      resultsTree.expandPath(tp); 
      resultsTree.makeVisible(tp);
	}
}

