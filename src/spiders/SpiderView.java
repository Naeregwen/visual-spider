package spiders;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.io.File;
import java.util.Collections;
import java.util.Enumeration;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JToolBar;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

import spiders.components.ColoredTee;
import spiders.components.ParametersPanel;
import spiders.components.Renamer;
import spiders.components.renderers.VisitedURLRenderer;
import spiders.components.workers.NodeAdder;
import spiders.constants.Constants;
import spiders.datas.Profile;
import spiders.datas.SearchResults;
import spiders.datas.VisitedURL;
import spiders.datas.Profile.Selection;
import spiders.parsers.ParametersParser;
import spiders.util.StringExt;
import spiders.util.XmlFilter;



public class SpiderView extends JFrame {

	// Spider user for searching the web
	private Spider spider; 
	
	// Profiles list used by spider
	private Vector<Profile> profiles;  //  @jve:decl-index=0:
	
	// Current profile
	private Profile currentProfile = null;  //  @jve:decl-index=0:
	
	// flag a loaded search 
	boolean searchLoaded = false;
	
	// File chooser for user input
	private final JFileChooser profileChooser = new JFileChooser();
	
	// Timer gérant le clignotement du bouton pause
	private static final int blinkInterval = 500; // intervalle de clignotement du bouton pause en millisecondes
	private Timer pauseBlinker = new PauseButtonBlinker(blinkInterval);  //  @jve:decl-index=0:
	
	private static final long serialVersionUID = -7100510940612890986L;
	private JPanel jContentPane = null;
	private JToolBar toolbar = null;
	private JLabel startSiteLabel = null;
	private JTextField startSiteField = null;
	private JButton startButton = null;
	private JButton stopButton = null;
	private JTabbedPane tabPane = null;
	private JScrollPane treePane = null;
	private JScrollPane messagesScrollPane = null;
	private JTree resultsTree = null;
	private JButton pauseButton = null;
	private JButton exitButton = null;
	private JPanel parametersPane = null;
	private JLabel statusLabel = null;
	private ParametersPanel parametersPanel = null;	
	private JPanel messagesPane = null;
	private JToolBar jToolBar = null;
	private JButton jButton = null;
	private JButton jButton1 = null;
	private JButton jButton2 = null;
	private JButton jButton3 = null;
	private JButton jButton4 = null;
	private JPanel subdomainPane = null;
	private JLabel jLabel = null;
	private JPanel resultsPane = null;
	private JTextArea pageStatistics = null;
	private JLabel jLabel3 = null;
	private JComboBox<String> profileField = null;
	private JTextPane messagesArea = null;
	private JLabel jLabel1 = null;
	private JScrollPane jScrollPane = null;
	private JScrollPane jScrollPane1 = null;
	private JTextArea jTextArea = null;
	private JTextArea jTextArea1 = null;
	private JMenuBar jJMenuBar = null;
	
	private JMenu jMenu = null;
	private JMenuItem jMenuItem = null;
	private JMenuItem jMenuItem1 = null;
	private JMenuItem jMenuItem2 = null;
	private JMenuItem jMenuItem3 = null;
	private JMenuItem jMenuItem4 = null;
	private JMenuItem jMenuItem5 = null;
	
	/**
	 * This is the default constructor
	 */
	public SpiderView() {
		super();
		initialize();		
	}
	
	/**
	 * This method initialize this
	 * 
	 * @return void
	 */
	private void initialize() {
		//
		// create a spider
		//
		spider = new Spider(new Profile(), this);
		//
		// center the frame on the screen
		//
		this.setSize(770, 666);
		Dimension oursize = getSize();
		Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(
				Math.max(0,(screensize.width - oursize.width)/2),
				Math.max(0,(screensize.height - oursize.height)/2)); 
		//
		// build view
		//
		this.setJMenuBar(getJJMenuBar());
		this.setContentPane(getJContentPane());
		this.setTitle("Web Spider");
		resetResultsTree();
	    resultsTree.setCellRenderer(new VisitedURLRenderer()); // use a custom cell renderer
	    pauseButton.setEnabled(false);
	    stopButton.setEnabled(false);
	    //
	    // setup configuration
	    //
	    loadConfiguration();
	    //
	    // setup profiles list and spider profile
	    //
	    loadProfilesFromFile(null);
	    //
	    // setup dialog
	    //
		profileChooser.setFileFilter(new XmlFilter());
		profileChooser.setCurrentDirectory(new File("."));

	}
	
	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			BorderLayout borderLayout = new BorderLayout();
			jContentPane = new JPanel();
			jContentPane.setLayout(borderLayout);
			jContentPane.add(getToolbar(), BorderLayout.NORTH);
			jContentPane.add(getTabPane(), BorderLayout.CENTER);
			jContentPane.add(getStatusLabel(), BorderLayout.SOUTH);
		}
		return jContentPane;
	}

	/**
	 * This method initializes toolbar	
	 * 	
	 * @return javax.swing.JToolBar	
	 */
	private JToolBar getToolbar() {
		if (toolbar == null) {
			jLabel3 = new JLabel();
			jLabel3.setText("Profile : ");
			startSiteLabel = new JLabel();
			startSiteLabel.setText(" Start Site : ");
			toolbar = new JToolBar();
			toolbar.add(jLabel3);
			toolbar.add(getProfileField());
			toolbar.add(startSiteLabel);
			toolbar.add(getStartSiteField());
			toolbar.add(getStartButton());
			toolbar.add(getPauseButton());
			toolbar.add(getStopButton());
			toolbar.add(getExitButton());
		}
		return toolbar;
	}

	/**
	 * This method initializes startSiteField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getStartSiteField() {
		if (startSiteField == null) {
			startSiteField = new JTextField();
			startSiteField.setEditable(true);
		}
		return startSiteField;
	}

	public void profileFieldChanged(java.awt.event.ItemEvent e) {
		if (e.getStateChange() == ItemEvent.SELECTED) {
			System.out.println("profileField selected " + profileField.getSelectedItem().toString());
			// search for first correponding profile
			for (Profile profile : profiles)
				if (profileField.getSelectedItem().toString().equals(profile.getName())){
					setProfilePanel(currentProfile = profile);
					startSiteField.setText(currentProfile.getStartSite().toString());
					break;
				}
		} else if (e.getStateChange() == ItemEvent.DESELECTED) {
			if (currentProfile != null) {
				Profile profile = getCurrentProfile();
				profile.setName(e.getItem().toString());
				currentProfile.clone(profile);
			}
		}
	}
	
	public void startSiteFieldChanged(java.awt.event.ItemEvent e) {		
		if (!startSiteField.isEnabled()) return;
		/*
		if (e.getStateChange() == ItemEvent.SELECTED) {
			System.out.println("startSiteField selected");
			// search for unique site in startSite list
			boolean startSiteExists = false;
			boolean startSiteExistsTwice = false;
			String url = startSiteField.getSelectedItem().toString();
			for (int i = 0; i < startSiteField.getItemCount(); i++) 
				if (url.equalsIgnoreCase(startSiteField.getItemAt(i).toString())) {
					if (startSiteExists) startSiteExistsTwice = true;
					else startSiteExists = true;	
					if (startSiteExistsTwice) break;
				}
			Profile selectedProfile = null;
			for (Profile profile : profiles) {				
				if (url.equals(profile.getStartSite().toString())) {
					selectedProfile = profile;
					break;
				}
			}
			
			if (startSiteExists)
				if (startSiteExistsTwice) 
					clearSubDomains();
				else {
					if (selectedProfile != null && currentProfile != selectedProfile) {
						setProfile(currentProfile = selectedProfile);
						profileField.setSelectedItem(currentProfile.getName());									
					}
				}
		} else if (e.getStateChange() == ItemEvent.DESELECTED) {
			System.out.println("startSiteField deselected");
			if (currentProfile != null) {
				Profile profile = getProfile();
				profile.setStartSite(e.getItem().toString());
				currentProfile.set(profile);
			}
		}
		*/
	}
	
	/**
	 * This method initializes startButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getStartButton() {
		if (startButton == null) {
			startButton = new JButton();
			startButton.setText("Start");
			startButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
			        if (hasBlankValue()) return;
					startButtonActionPerformed(e);
				}
			});
		}
		return startButton;
	}

	/**
	 * This method initializes pauseButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getPauseButton() {
		if (pauseButton == null) {
			pauseButton = new JButton();
			pauseButton.setText("Pause");
			pauseButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					pauseButtonActionPerformed(e);
				}
			});
		}
		return pauseButton;
	}

	/**
	 * This method initializes stopButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getStopButton() {
		if (stopButton == null) {
			stopButton = new JButton();
			stopButton.setText("Stop");
			stopButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					stopButtonActionPerformed(e);
				}
			});
		}
		return stopButton;
	}

	/**
	 * This method initializes exitButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getExitButton() {
		if (exitButton == null) {
			exitButton = new JButton();
			exitButton.setText("Exit");
			exitButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					exitButtonActionPerformed(e);
				}
			});
		}
		return exitButton;
	}

	/**
	 * This method initializes tabPane	
	 * 	
	 * @return javax.swing.JTabbedPane	
	 */
	private JTabbedPane getTabPane() {
		if (tabPane == null) {
			tabPane = new JTabbedPane();
			tabPane.addTab("Parameters", null, getParametersPane(), null);
			tabPane.addTab("Subdomains", null, getSubdomainPane(), null);
			tabPane.addTab("Results", null, getResultsPane(), null);
			tabPane.addTab("Messages", null, getMessagesPane(), null);
		}
		return tabPane;
	}

	/**
	 * This method initializes parametersPane	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getParametersPane() {
		if (parametersPane == null) {
			FlowLayout flowLayout = new FlowLayout();
			flowLayout.setAlignment(FlowLayout.LEFT);
			parametersPane = new JPanel();
			parametersPane.setLayout(new CardLayout());
			parametersPanel = new ParametersPanel(getStartButton());
			parametersPanel.setLayout(flowLayout);
			parametersPane.add(parametersPanel, "jPanel");

		}
		return parametersPane;
	}

	/**
	 * This method initializes treePane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getTreePane() {
		if (treePane == null) {
			treePane = new JScrollPane();
			treePane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
			treePane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
			treePane.setViewportView(getResultsTree());
		}
		return treePane;
	}

	/**
	 * This method initializes messagesScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getMessagesScrollPane() {
		if (messagesScrollPane == null) {
			messagesScrollPane = new JScrollPane();
			messagesScrollPane.setViewportView(getMessagesArea());
		}
		return messagesScrollPane;
	}

	/**
	 * This method initializes resultsTree	
	 * 	
	 * @return javax.swing.JTree	
	 */
	public JTree getResultsTree() {
		if (resultsTree == null) {
			resultsTree = new JTree();
			resultsTree.setBounds(new Rectangle(0, 0, 0, 0));
			resultsTree.addTreeSelectionListener(new javax.swing.event.TreeSelectionListener() {
						public void valueChanged(javax.swing.event.TreeSelectionEvent e) {
							resultsTreeSelectionChange(e);
						}
					});
		}
		return resultsTree;
	}

	/**
	 * This method initializes statusBar	
	 * 	
	 * @return javax.swing.JToolBar	
	 */
	private JLabel getStatusLabel() {
		if (statusLabel == null) {
			statusLabel = new JLabel();
			statusLabel.setText("Status");
		}
		return statusLabel;
	}

	/**
	 * This method initializes messagesPane	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getMessagesPane() {
		if (messagesPane == null) {
			messagesPane = new JPanel();
			messagesPane.setLayout(new BorderLayout());
			messagesPane.add(getJToolBar(), BorderLayout.NORTH);
			messagesPane.add(getMessagesScrollPane(), BorderLayout.CENTER);
		}
		return messagesPane;
	}

	/**
	 * This method initializes jToolBar	
	 * 	
	 * @return javax.swing.JToolBar	
	 */
	private JToolBar getJToolBar() {
		if (jToolBar == null) {
			jToolBar = new JToolBar();
			jToolBar.add(getJButton1());
			jToolBar.add(getJButton2());
			jToolBar.add(getJButton3());
			jToolBar.add(getJButton());
			jToolBar.add(getJButton4());
		}
		return jToolBar;
	}

	/**
	 * This method initializes jButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButton() {
		if (jButton == null) {
			jButton = new JButton();
			jButton.setText("clear messages");
			jButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					messagesArea.setText("");				
				}
			});
		}
		return jButton;
	}

	/**
	 * This method initializes jButton1	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButton1() {
		if (jButton1 == null) {
			jButton1 = new JButton();
			jButton1.setText("Select all");
			jButton1.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					messagesArea.selectAll();
					messagesArea.getCaret().setSelectionVisible(true);
				}
			});
		}
		return jButton1;
	}

	/**
	 * This method initializes jButton2	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButton2() {
		if (jButton2 == null) {
			jButton2 = new JButton();
			jButton2.setText("Deselect all");
			jButton2.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					messagesArea.select(0,0);
					messagesArea.getCaret().setSelectionVisible(true);
				}
			});
		}
		return jButton2;
	}

	/**
	 * This method initializes jButton3	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButton3() {
		if (jButton3 == null) {
			jButton3 = new JButton();
			jButton3.setText("clear selected");
			jButton3.addActionListener(new java.awt.event.ActionListener() {   
				public void actionPerformed(java.awt.event.ActionEvent e) {    
					if (messagesArea.getSelectedText() != null && messagesArea.getSelectedText().length() > 0)
						messagesArea.cut();
				}
			
			});
		}
		return jButton3;
	}

	/**
	 * This method initializes jButton4	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButton4() {
		if (jButton4 == null) {
			jButton4 = new JButton();
			jButton4.setText("clear all");
			jButton4.addActionListener(new java.awt.event.ActionListener() {   
				public void actionPerformed(java.awt.event.ActionEvent e) {    
					messagesArea.setText("");
					statusLabel.setText(" ");
				}
			
			});
		}
		return jButton4;
	}

	/**
	 * This method initializes subdomainPane	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getSubdomainPane() {
		if (subdomainPane == null) {
			GridBagConstraints gridBagConstraints11 = new GridBagConstraints();
			gridBagConstraints11.fill = GridBagConstraints.BOTH;
			gridBagConstraints11.gridy = 1;
			gridBagConstraints11.weightx = 1.0;
			gridBagConstraints11.weighty = 1.0;
			gridBagConstraints11.gridx = 1;
			GridBagConstraints gridBagConstraints10 = new GridBagConstraints();
			gridBagConstraints10.fill = GridBagConstraints.BOTH;
			gridBagConstraints10.gridy = 1;
			gridBagConstraints10.weightx = 1.0;
			gridBagConstraints10.weighty = 1.0;
			gridBagConstraints10.gridx = 0;
			GridBagConstraints gridBagConstraints9 = new GridBagConstraints();
			gridBagConstraints9.gridx = 1;
			gridBagConstraints9.gridy = 0;
			jLabel1 = new JLabel();
			jLabel1.setText("included URL");
			GridBagConstraints gridBagConstraints = new GridBagConstraints();
			gridBagConstraints.gridx = 0;
			gridBagConstraints.gridy = 0;
			jLabel = new JLabel();
			jLabel.setText("excluded URL");
			subdomainPane = new JPanel();
			subdomainPane.setLayout(new GridBagLayout());
			subdomainPane.add(jLabel, gridBagConstraints);
			subdomainPane.add(jLabel1, gridBagConstraints9);
			subdomainPane.add(getJScrollPane(), gridBagConstraints10);
			subdomainPane.add(getJScrollPane1(), gridBagConstraints11);
		}
		return subdomainPane;
	}

	/**
	 * This method initializes resultsPane	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getResultsPane() {
		if (resultsPane == null) {
			resultsPane = new JPanel();
			resultsPane.setLayout(new BorderLayout());
			resultsPane.add(getTreePane(), BorderLayout.CENTER);
			resultsPane.add(getPageStatistics(), BorderLayout.SOUTH);
		}
		return resultsPane;
	}

	/**
	 * This method initializes pageStatistics	
	 * 	
	 * @return javax.swing.JTextArea	
	 */
	private JTextArea getPageStatistics() {
		if (pageStatistics == null) {
			pageStatistics = new JTextArea();
			pageStatistics.setPreferredSize(new Dimension(0, 66));
			pageStatistics.setBackground(Color.lightGray);
			pageStatistics.setText("Statistics");
			pageStatistics.setEnabled(true);
		}
		return pageStatistics;
	}

	/**
	 * This method initializes profileField	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox<String> getProfileField() {
		if (profileField == null) {
			profileField = new JComboBox<String>();
			profileField.setEditable(true);
			profileField.addItemListener(new java.awt.event.ItemListener() {
				public void itemStateChanged(java.awt.event.ItemEvent e) {
					profileFieldChanged(e);
				}
			});
		}
		return profileField;
	}

	/**
	 * This method initializes messagesArea	
	 * 	
	 * @return javax.swing.JTextPane	
	 */
	private JTextPane getMessagesArea() {
		if (messagesArea == null) {
			messagesArea = new JTextPane();
			messagesArea.setEditable(false);
			messagesArea.setSize(new Dimension(0, 2000));
		}
		return messagesArea;
	}

	/**
	 * This method initializes jScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane() {
		if (jScrollPane == null) {
			jScrollPane = new JScrollPane();
			jScrollPane.setViewportView(getJTextArea());
		}
		return jScrollPane;
	}

	/**
	 * This method initializes jScrollPane1	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane1() {
		if (jScrollPane1 == null) {
			jScrollPane1 = new JScrollPane();
			jScrollPane1.setViewportView(getJTextArea1());
		}
		return jScrollPane1;
	}

	/**
	 * This method initializes jTextArea	
	 * 	
	 * @return javax.swing.JTextArea	
	 */
	private JTextArea getJTextArea() {
		if (jTextArea == null) {
			jTextArea = new JTextArea();
		}
		return jTextArea;
	}

	/**
	 * This method initializes jTextArea1	
	 * 	
	 * @return javax.swing.JTextArea	
	 */
	private JTextArea getJTextArea1() {
		if (jTextArea1 == null) {
			jTextArea1 = new JTextArea();
		}
		return jTextArea1;
	}

	/**
	 * This method initializes jJMenuBar	
	 * 	
	 * @return javax.swing.JMenuBar	
	 */
	private JMenuBar getJJMenuBar() {
		if (jJMenuBar == null) {
			jJMenuBar = new JMenuBar();
			jJMenuBar.add(getJMenu());
		}
		return jJMenuBar;
	}

	/**
	 * This method initializes jMenu	
	 * 	
	 * @return javax.swing.JMenu	
	 */
	private JMenu getJMenu() {
		if (jMenu == null) {
			jMenu = new JMenu();
			jMenu.setText("Site");
			jMenu.add(getJMenuItem1());
			jMenu.add(getJMenuItem2());
			jMenu.add(getJMenuItem3());
			jMenu.addSeparator();
			jMenu.add(getJMenuItem4());
			jMenu.add(getJMenuItem5());
			jMenu.addSeparator();
			jMenu.add(getJMenuItem());

		}
		return jMenu;
	}

	/**
	 * This method initializes jMenuItem1	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getJMenuItem1() {
		if (jMenuItem1 == null) {
			jMenuItem1 = new JMenuItem();
			jMenuItem1.setText("Load profiles...");
			jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					loadProfiles();
				}
			});
		}
		return jMenuItem1;
	}

	/**
	 * This method initializes jMenuItem2	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getJMenuItem2() {
		if (jMenuItem2 == null) {
			jMenuItem2 = new JMenuItem();
			jMenuItem2.setText("Save current profile...");
			jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					saveProfiles (Profile.Selection.CURRENT_PROFILE);
				}
			});
		}
		return jMenuItem2;
	}

	/**
	 * This method initializes jMenuItem	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getJMenuItem() {
		if (jMenuItem == null) {
			jMenuItem = new JMenuItem();
			jMenuItem.setText("Exit");
			jMenuItem.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.exit(0);
				}
			});
		}
		return jMenuItem;
	}

	/**
	 * This method initializes jMenuItem3	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getJMenuItem3() {
		if (jMenuItem3 == null) {
			jMenuItem3 = new JMenuItem();
			jMenuItem3.setText("Save all profiles...");
			jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					saveProfiles(Profile.Selection.ALL_PROFILES);
				}
			});
		}
		return jMenuItem3;
	}

	/**
	 * This method initializes jMenuItem4	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getJMenuItem4() {
		if (jMenuItem4 == null) {
			jMenuItem4 = new JMenuItem();
			jMenuItem4.setText("Load search...");
			jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					loadSearch();
				}
			});
		}
		return jMenuItem4;
	}

	/**
	 * This method initializes jMenuItem5	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getJMenuItem5() {
		if (jMenuItem5 == null) {
			jMenuItem5 = new JMenuItem();
			jMenuItem5.setText("Save search...");
			jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					saveCurrentSearch();
				}
			});
		}
		return jMenuItem5;
	}
	
	/**
	 * get the tee associated with this view and its spider
	 * 
	 */
	public ColoredTee getTee() {
		return spider != null ? spider.getTee() : null;
	}
	
	/**
	 * classe artificielle gérant le clignotement du bouton pause
	 * Elle permet d'intégrer la notion de couleur aux méthodes du Timer swing (start et stop)
	 * 
	 */
	private class PauseButtonBlinker extends Timer {

		private static final long serialVersionUID = 2L; // sérialisation
		private final Color upColor = Color.RED; // couleur de bouton à l'état allumé
		private final Color color = getPauseButton().getForeground(); // couleur du bouton au repos

		/**
		 * constructeur par defaut d'un gestionnaire de clignotement 
		 * 
		 * @param interval	intervalle en millisecondes entre les changements de couleur du bouton
		 * 
		 * @return void
		 */
		public PauseButtonBlinker(int interval) {
			super(interval, new ActionListener() {
				private boolean blink; // statut du clignotement
				private final Color upColor = Color.RED; // couleur de bouton à l'état allumé
				private final Color color = getPauseButton().getForeground(); // couleur du bouton au repos
				public void actionPerformed(ActionEvent evt) {
					// changer la couleur d'avant plan du bouton pause tous les blinkInterval
					getPauseButton().setForeground((blink = !blink) ? color : upColor);
				}
			}); 
		}
		
		/**
		 * mise au repos du bouton et arrêt du clignotement
		 * 
		 * @return void
		 */
		public void stop() {
			pauseButton.setForeground(color);
			super.stop();
			
		}
		
		/**
		 * allumage du bouton et démarrage du clignotement
		 * 
		 * @return void
		 */
		public void start() {
			pauseButton.setForeground(upColor);
			super.start();
		}
	}

	/**
	 * This method start the spider	
	 * 
	 */
    private void startButtonActionPerformed(java.awt.event.ActionEvent evt) {
    	
    	//
    	// retrieve startSite and create parameters
    	//
        String startSite = getStartSite();
        if (startSite.trim().length() <= 0) {
          showDialog("Starting web site cannot be blank!");
          return;
        }
        //
        // start the spider
        //
        if (spider != null) {
        	updateCommandButtons(Spider.Status.RUNNING);
        	disableSearchInputFields();
        	spider.setProfile(getCurrentProfile(), startSite);
        	resetResultsTree();
        	startSpider();
        }
    }

    private void startSpider() {
    	tabPane.setSelectedComponent(getResultsPane());       	
    	spider.start();
    }
    
    private void disableSearchInputFields() {
    	profileField.setEnabled(false); 
    	startSiteField.setEnabled(false);
    }
    
    private void pauseButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pauseButtonActionPerformed
    	System.out.println("entering pause : " +spider.getStatus());
    	if (!currentProfile.equals(getCurrentProfile())) System.out.println("currentProfile is different from getCurrentProfile");
		// check profile change 
		//if (!searchLoaded
		if (//spider.getStatus() != Spider.Status.STOPPED
				//&& 
				spider.getStatus() == Spider.Status.PAUSED 
				&& !hasBlankValue() 
				&& !currentProfile.equals(getCurrentProfile())) {
			int choice = JOptionPane.showConfirmDialog(this, "Parameters have changed ?\nWould you like to use the new ones (yes), keep the old ones (no), or cancel resume ?");			
			switch (choice) {
			case javax.swing.JOptionPane.YES_OPTION : spider.setProfile((currentProfile = getCurrentProfile()), null); break;	
			case javax.swing.JOptionPane.NO_OPTION : break;	
			case javax.swing.JOptionPane.CANCEL_OPTION : return;
			default: return;
			}
		}
		if (searchLoaded) {			
			searchLoaded = false;
			pauseBlinker.stop();
			startSpider();
		} else
			pauseSpider(false);
    	System.out.println("exiting pause : " +spider.getStatus());
    }
    
	/**
	 * This method switch a spider between RUNNING and PAUSED state 
	 * then start or stop timer according to the new spider state 	
	 * 
	 */
    private void pauseSpider(boolean forced) {
		// mise à jour de l'interface graphique
		updateCommandButtons(forced ? Spider.Status.PAUSED : spider.switchSuspended());
		// démarrer ou stopper le timer selon le statut
		if (searchLoaded || spider.getStatus() == Spider.Status.PAUSED)
			pauseBlinker.start();
		else 
			pauseBlinker.stop();
    }
	
	/**
	 * This method stop a spider	
	 * 
	 */
    private void stopButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stopButtonActionPerformed
		if (spider != null) {
			// mise à jour de l'interface graphique
			updateCommandButtons(spider.stopSearch());
			// stopper le timer pause
			pauseBlinker.stop();
			enableSite();
		}
    }
    
    public void enableSite() {
    	profileField.setEnabled(true);
    	startSiteField.setEnabled(true);
    }
    
	/**
	 * This method exit from spiderView
	 * 
	 */
    private void exitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitButtonActionPerformed
    	if (spider != null) {
    		switch (spider.getStatus()) {
    		case RUNNING: spider.stopSearch(); break;
    		case PAUSED:spider.switchSuspended(); break;
    		default: break;
    		}
    	}
    	System.exit(0);
    }
    
	private void resultsTreeSelectionChange(javax.swing.event.TreeSelectionEvent evt) {
		TreePath path = resultsTree.getSelectionPath();
		if (path == null) return;
		DefaultMutableTreeNode node = (DefaultMutableTreeNode)path.getLastPathComponent();
		VisitedURL data = (VisitedURL)node.getUserObject();
		pageStatistics.setText(data != null && data instanceof VisitedURL ? data.getNodeStats() : "");

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				SpiderView thisClass = new SpiderView();
				thisClass.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				thisClass.setVisible(true);
			}
		});
	}

    //
    // check for valid subdomains from startsite
    // 
	/**
	 * get current profile
	 */
    private Profile getCurrentProfile() {
    	
    	// define new parameters with start url site
        Profile profile = getStartSite() != null ? new Profile(getStartSite()) : new Profile();
        //
        // retrieve name, allowed extensions, excluded subdomains, included subdomains
        //
        profile.setName(getProfileName());
        profile.setAllowedExtensions(parseTextArea(parametersPanel.getAllowedExtensions()));
        profile.setExcludedURL(parseTextArea(getExcludedSubdomains()));
        profile.setIncludedURL(parseTextArea(getIncludedSubdomains()));
        // Crawling options
        profile.setMethod(parametersPanel.getMethod());
        profile.setMaxURLtoVisit(parametersPanel.getMaxURLtoVisit());
        profile.setMaxDepth(parametersPanel.getMaxDepth());
        profile.setStayInDomain(parametersPanel.getStayInDomain());
        profile.setCountImages(parametersPanel.getCountImages());
    	// verbosity options
        profile.setVerbose(parametersPanel.getVerbose());
        profile.setUseConsole(parametersPanel.getUseConsole());
        profile.setSummarizeCrawling(parametersPanel.getSummarizeCrawling());
        profile.setShowInvalidDomains(parametersPanel.getShowInvalidDomains());
        profile.setShowInvalidURL(parametersPanel.getShowInvalidURL());
        profile.setShowVisitedURL(parametersPanel.getShowVisitedURL());
        profile.setShowSkippedURL(parametersPanel.getShowSkippedURL());
        profile.setShowSkippedForDepthURL(parametersPanel.getShowSkippedForDepthURL());
        profile.setShowEncodingProblems(parametersPanel.getShowEncodingProblems());
        profile.setShowIllegalArguments(parametersPanel.getShowIllegalArguments());

        return profile;
    }

   public Boolean hasBlankValue() {
    	String maxURL = parametersPanel.getFieldMaxURLtoVisit();
    	String maxDepth = parametersPanel.getFieldMaxDepth(); 
		if (maxURL == null || maxDepth == null 
				|| maxURL.trim().length() == 0 || maxDepth.trim().length() == 0) 
			return true;
    	return false;
    }
    private Vector<String> parseTextArea(String text) {    	
    	StringTokenizer tokens = new StringTokenizer(text, "\n");
    	Vector<String> lines = new Vector<String>(tokens.countTokens());
    	while (tokens.hasMoreTokens()) lines.add(tokens.nextToken());
    	return lines;
    }

    private String getStartSite() {
    	return ((JTextField) startSiteField).getText();
    }
    
    private String getProfileName() {
    	return ((JTextField) profileField.getEditor().getEditorComponent()).getText();
    }
    
	public void setSubDomains(Profile parameters){
		clearSubDomains();
		Vector<String> url = parameters != null ? (parameters.getExcludedURL()) : spider.getParameters().getExcludedURL();
		for (String excluded : url) 
			if (!excluded.trim().equals("")) jTextArea.append(excluded+"\n");
		url = parameters != null ? (parameters.getIncludedURL()) : spider.getParameters().getIncludedURL();
		for (String included : url) 
			if (!included.trim().equals("")) jTextArea1.append(included+"\n");		
	}
	public void clearSubDomains() { jTextArea.setText(""); jTextArea1.setText(""); }
	
	public String getExcludedSubdomains() { return jTextArea.getText(); }
	public String getIncludedSubdomains() { return jTextArea1.getText(); }
	
    /*
     * Message Area modification
     */
	public void appendMessage(String message, Color color) {
		Document document = messagesArea.getDocument();
		SimpleAttributeSet attributes = new SimpleAttributeSet();
	    StyleConstants.setForeground(attributes, color);
		try {
			document.insertString(document.getLength(), message, attributes);
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
	}
	
    /**
     * Message Area modification
     */
	public void setStatus(String message) {
		statusLabel.setText(message);
	}
	
	/**
	 * Dialogs
	 */
	public void showDialog(String message) {
		JOptionPane.showMessageDialog(this, message);		
	}

	/**
	 * update parameters panel & subdomains according to given profile
	 * 
	 * @param profile the profile to use
	 */
	private void setProfilePanel(Profile profile) {
		// setup parameters
		parametersPanel.setParameters(profile != null ? profile : new Profile());
		// setup subdomains
		setSubDomains(profile != null ? profile : new Profile());
	}
	
	/**
	 * get a list of all availables profiles 
	 * 
	 * @return profiles a list of all availables profiles
	 */
	private Vector<String> getAllProfiles() {
		Vector<String> profiles = new Vector<String>();
		for (int i = 0; i < profileField.getItemCount(); i++)
			profiles.add((String)profileField.getItemAt(i));
		return profiles;
	}
	
	/**
	 * Start, pause, stop buttons
	 */
	public void updateCommandButtons(Spider.Status status) {
		switch (status) {
		case RUNNING:
			startButton.setEnabled(false);
			pauseButton.setEnabled(true);
			stopButton.setEnabled(true);
			break;
		case PAUSED:
			startButton.setEnabled(false);
			pauseButton.setEnabled(true);
			stopButton.setEnabled(true);
			break;
		case STOPPED:
			startButton.setEnabled(true);
			pauseButton.setEnabled(false);
			stopButton.setEnabled(false);
			break;
		}
	}

    /**
     * resultTree methods
     *
     */
	
    /**
     * reset resultsTree
     * 
     */
    private void resetResultsTree() {
	    resultsTree.setModel(new DefaultTreeModel(new DefaultMutableTreeNode(new VisitedURL("Root"))));
    }

    /**
     * add given url to resultsTree
     * 
     * @param url url to add
     */
	public void addURL(VisitedURL url) {
		SwingUtilities.invokeLater(new NodeAdder(url, getResultsTree()));		
	}
	
	 /**
	 * Verifier listener routine used to report bad data
	 *  @param message error message
	 *  @param jComponent component that caused the error.
	 */
	public void invalidData(String message, JComponent jComponent) {
		//errorLabel.setText(message);
		//errorLabel.setForeground(Color.red);
		startButton.setEnabled(false); // turn off the start button
		getToolkit().beep();
	}

	/**
	 * Verifier listener routine used to report good data
	 *  @param jComponent component that has tested ok
	 */
	public void validData(JComponent jComponent) {
		//errorLabel.setText("  ");
		startButton.setEnabled(true); // turn on the start button
	}
	    
	/**
	 * Profiles commands
	 * 
	 */
	private void loadProfiles() {
        if (profileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION)
        	loadProfilesFromFile(profileChooser.getSelectedFile());
 	}
	
	private void loadProfilesFromFile(File file) {
		
		Vector<Profile> newProfiles = Profile
			.loadFromFile(file != null ? 
					file.getAbsoluteFile().toString() : 
					Constants.PROF, ParametersParser.ParsingMethod.PROFILES, this);		
		if (newProfiles != null && newProfiles.size() > 0 )
			addProfiles(newProfiles, file);
	}

	private boolean addProfiles(Vector<Profile> newProfiles, File file) {
		
		Vector<String> profileNames = new Vector<String>();

		if (file == null) { // file is null during initialization
			profileField.removeAllItems();
			if (profiles == null) 
				profiles = new Vector<Profile>();
			else
				profiles.removeAllElements();
		} else 
			profileNames.addAll(getAllProfiles());
		
		// setup profiles suggest
		Profile firstProfile = null; // first profile added
		for (Profile profile : newProfiles) {
			// search if profile name already exists
			if (file != null) {
				for (int i = 0; i < profiles.size(); i++) 
					if (profile.getName().equalsIgnoreCase(profileField.getItemAt(i).toString())) {
						Renamer renamer  = new Renamer(this, "profile name", profile.getName(), getAllProfiles());
						renamer.setVisible(true);
						String name = renamer.getName();
						if (name == null) return false;
						profile.setName(name);
						break;
					}
			}
			profileNames.add(profile.getName());
			profiles.add(profile);
			if (firstProfile == null) firstProfile = profile;
		}
		
		// sort profiles name list, ignoring case
		Collections.sort(profileNames, StringExt.IgnoreCaseComparator);
		
		// add collection to profileField list
		profileField.removeAllItems();
		for (String profileName : profileNames) profileField.addItem(profileName);

		// Setup spider selected profile with first profile loaded
		if (firstProfile == null) return false;
		profileField.setSelectedItem(firstProfile.getName());
		spider.setProfile(currentProfile, null);
		return true;
	}
	
	private void saveProfiles (Selection selection) {
		if (selection == Profile.Selection.CURRENT_SEARCH) return;
		int answer = JOptionPane.NO_OPTION;
		while (answer != JOptionPane.CANCEL_OPTION 
				&& profileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
			if ((profileChooser.getSelectedFile().exists()
						&& (answer = JOptionPane.showConfirmDialog(this, 
						"Are you sure you want to overwrite file "+profileChooser.getSelectedFile()+"?", 
						"Confirm overwrite", 
						JOptionPane.YES_NO_OPTION,
						JOptionPane.INFORMATION_MESSAGE)) == JOptionPane.YES_OPTION) 
						|| !profileChooser.getSelectedFile().exists())	{
				if (selection == Profile.Selection.ALL_PROFILES ?
						Profile.writeToFile(profiles, profileChooser.getSelectedFile(), spider.getTee()) :
						getCurrentProfile().writeToFile(profileChooser.getSelectedFile(), spider.getTee()))
					break;			
			}			
		}
	}
	
	private void loadConfiguration() {
		Vector<Profile> defaultParameters = Profile.loadFromFile(Constants.CONF, ParametersParser.ParsingMethod.CONFIGURATION, this);
		setProfilePanel(defaultParameters != null ? defaultParameters.firstElement() : new Profile());
	}
	
	/**
	 * Search Results commands
	 * 
	 */
	
	/**
	 * Save the current search
	 * 
	 */
	private void saveCurrentSearch() {
		
		if (spider.getStatus() != Spider.Status.STOPPED) return;
		if (countNodes() <= 1) {
			JOptionPane.showMessageDialog(this, "No search done");
			return;
		}
		
		int answer = JOptionPane.NO_OPTION;
		File selectedFile = null;
		while (answer != JOptionPane.CANCEL_OPTION 
				&& profileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
			if (((selectedFile = profileChooser.getSelectedFile()).exists()
						&& (answer = JOptionPane.showConfirmDialog(this, 
						"Are you sure you want to overwrite file "+selectedFile+"?", 
						"Confirm overwrite", 
						JOptionPane.YES_NO_OPTION,
						JOptionPane.INFORMATION_MESSAGE)) == JOptionPane.YES_OPTION) 
					|| !selectedFile.exists())	{
				if (new SearchResults(this, getCurrentProfile(), spider.getURLToVisit())
						.write(selectedFile))
					break;
			}			
		}		
	}
	
	private int countNodes() {
		int size = 0;
		Enumeration<?> e = ((DefaultMutableTreeNode)resultsTree.getModel().getRoot()).breadthFirstEnumeration();
		while (e.hasMoreElements() && e.nextElement() != null) size++;
		return size;
	}
	
	/**
	 * Load a search from file
	 *
	 */
	private void loadSearch() {
        if (profileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
        	TreeModel model = resultsTree.getModel();
        	resetResultsTree();
        	SearchResults results = null;
        	if ((results = loadSearchFromFile(profileChooser.getSelectedFile())) != null) { 
        		Vector<Profile> newProfiles = new Vector<Profile>();
        		newProfiles.add(new Profile((Profile)results));
        		if (addProfiles(newProfiles, profileChooser.getSelectedFile())) {
        			spider.setSearch(results.getURLToVisit(), results.getVisitedURL());
        			disableSearchInputFields();
        			searchLoaded = true;
        			pauseSpider(true);
        		} else
    				resultsTree.setModel(model);
        	} else
				resultsTree.setModel(model);
        }
		
	}
	
	private SearchResults loadSearchFromFile(File file) {
		return SearchResults.loadFromFile(file.getAbsoluteFile().toString(), this);
	}
	
}  //  @jve:decl-index=0:visual-constraint="134,-77"
