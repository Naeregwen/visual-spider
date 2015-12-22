package spiders.components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.SoftBevelBorder;

import spiders.components.listeners.IntegerVerifier;
import spiders.components.listeners.VerifierListener;
import spiders.constants.Constants;
import spiders.constants.Constants.METHOD;
import spiders.constants.Constants.SITESIZE;
import spiders.datas.Profile;


public class ParametersPanel extends JPanel implements VerifierListener{

	private static final long serialVersionUID = 1L;
	
	private JPanel jContentPane = null;
	private JPanel jPanel = null;
	private JPanel optionsPanel = null;
	private JLabel crawlingMethodLabel = null;
	private JRadioButton jRadioButton = null;
	private JRadioButton jRadioButton1 = null;
	private JLabel jLabel3 = null;
	private JTextField jTextField = null;
	private JLabel jLabel4 = null;
	private JTextField jTextField1 = null;
	private JLabel jLabel5 = null;
	private JRadioButton jRadioButton2 = null;
	private JRadioButton jRadioButton3 = null;
	private JPanel optionsPanel1 = null;
	private JLabel crawlingMethodLabel1 = null;
	private JRadioButton jRadioButton4 = null;
	private JRadioButton jRadioButton11 = null;
	private JLabel jLabel31 = null;
	private JRadioButton jRadioButton211 = null;
	private JRadioButton jRadioButton311 = null;
	private JScrollPane jScrollPane2 = null;
	private JTextArea jTextArea2 = null;
	private JLabel jLabel = null;
	private JLabel jLabel6 = null;
	private JLabel jLabel7 = null;
	private JLabel jLabel8 = null;
	private JLabel jLabel9 = null;
	private JLabel jLabel10 = null;
	private JLabel jLabel11 = null;
	private JLabel jLabel12 = null;
	private JToolBar jToolBar = null;
	private JButton jButton = null;
	private JButton jButton1 = null;
	private JButton jButton2 = null;
	private JToolBar jToolBar1 = null;
	private JButton jButton3 = null;
	private JButton jButton4 = null;
	private JButton jButton5 = null;
	private JButton jButton6 = null;
	private JLabel jLabel14 = null;
	private JButton jButton7 = null;
	private JLabel jLabel15 = null;
	private JButton jButton8 = null;
	private JToolBar jToolBar2 = null;
	private JLabel jLabel16 = null;
	private JButton jButton9 = null;
	private JButton jButton10 = null;
	private JButton jButton11 = null;
	private JLabel jLabel1 = null;
	private JRadioButton jRadioButton5 = null;
	private JRadioButton jRadioButton6 = null;
	private JLabel errorLabel = null;

	private JRadioButton jRadioButton2111 = null;
	private JRadioButton jRadioButton2112 = null;
	private JRadioButton jRadioButton2113 = null;
	private JRadioButton jRadioButton2114 = null;
	private JRadioButton jRadioButton2115 = null;
	private JRadioButton jRadioButton2116 = null;
	private JRadioButton jRadioButton2117 = null;
	private JRadioButton jRadioButton2118 = null;
	private JRadioButton jRadioButton3111 = null;
	private JRadioButton jRadioButton3112 = null;
	private JRadioButton jRadioButton3113 = null;
	private JRadioButton jRadioButton3114 = null;
	private JRadioButton jRadioButton3115 = null;
	private JRadioButton jRadioButton3116 = null;
	private JRadioButton jRadioButton3117 = null;
	private JRadioButton jRadioButton3118 = null;
	private JLabel jLabel13 = null;

	/**
	 * This method initializes jPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel() {
		if (jPanel == null) {
			errorLabel = new JLabel();
			errorLabel.setText("");
			errorLabel.setHorizontalTextPosition(SwingConstants.LEADING);
			errorLabel.setPreferredSize(new Dimension(400, 20));
			errorLabel.setHorizontalAlignment(SwingConstants.LEFT);
			jPanel = new JPanel();
			jPanel.setPreferredSize(new Dimension(400, 247));
			jPanel.setLayout(new BoxLayout(getJPanel(), BoxLayout.Y_AXIS));
			jPanel.add(getOptionsPanel(), null);
			jPanel.add(getOptionsPanel1(), null);
		}
		return jPanel;
	}

	/**
	 * This method initializes optionsPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getOptionsPanel() {
		if (optionsPanel == null) {
			GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
			gridBagConstraints2.gridx = 2;
			gridBagConstraints2.anchor = GridBagConstraints.WEST;
			gridBagConstraints2.gridy = 5;
			GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
			gridBagConstraints1.gridx = 1;
			gridBagConstraints1.anchor = GridBagConstraints.WEST;
			gridBagConstraints1.gridy = 5;
			GridBagConstraints gridBagConstraints = new GridBagConstraints();
			gridBagConstraints.gridx = 0;
			gridBagConstraints.anchor = GridBagConstraints.EAST;
			gridBagConstraints.gridy = 5;
			jLabel1 = new JLabel();
			jLabel1.setText("Get Images count : ");
			GridBagConstraints gridBagConstraints44 = new GridBagConstraints();
			gridBagConstraints44.fill = GridBagConstraints.HORIZONTAL;
			gridBagConstraints44.gridy = 6;
			gridBagConstraints44.weightx = 1.0;
			gridBagConstraints44.anchor = GridBagConstraints.WEST;
			gridBagConstraints44.gridwidth = 3;
			gridBagConstraints44.gridx = 0;
			GridBagConstraints gridBagConstraints43 = new GridBagConstraints();
			gridBagConstraints43.fill = GridBagConstraints.HORIZONTAL;
			gridBagConstraints43.gridy = 0;
			gridBagConstraints43.weightx = 1.0;
			gridBagConstraints43.gridwidth = 3;
			gridBagConstraints43.anchor = GridBagConstraints.WEST;
			gridBagConstraints43.gridx = 0;
			GridBagConstraints gridBagConstraints40 = new GridBagConstraints();
			gridBagConstraints40.gridx = 0;
			gridBagConstraints40.anchor = GridBagConstraints.NORTH;
			gridBagConstraints40.gridy = 7;
			jLabel13 = new JLabel();
			jLabel13.setText("Allowed file extensions : ");
			GridBagConstraints gridBagConstraints9 = new GridBagConstraints();
			gridBagConstraints9.fill = GridBagConstraints.BOTH;
			gridBagConstraints9.gridx = 1;
			gridBagConstraints9.gridy = 7;
			gridBagConstraints9.weightx = 0.0;
			gridBagConstraints9.weighty = 1.0;
			gridBagConstraints9.gridheight = 1;
			gridBagConstraints9.gridwidth = 1;
			GridBagConstraints gridBagConstraints41 = new GridBagConstraints();
			gridBagConstraints41.fill = GridBagConstraints.BOTH;
			gridBagConstraints41.gridy = 5;
			gridBagConstraints41.weightx = 1.0;
			gridBagConstraints41.weighty = 1.0;
			gridBagConstraints41.gridwidth = 2;
			gridBagConstraints41.gridx = 2;
			GridBagConstraints gridBagConstraints16 = new GridBagConstraints();
			gridBagConstraints16.anchor = GridBagConstraints.WEST;
			gridBagConstraints16.gridy = 4;
			gridBagConstraints16.gridx = 2;
			GridBagConstraints gridBagConstraints15 = new GridBagConstraints();
			gridBagConstraints15.anchor = GridBagConstraints.WEST;
			gridBagConstraints15.gridy = 4;
			gridBagConstraints15.gridx = 1;
			GridBagConstraints gridBagConstraints14 = new GridBagConstraints();
			gridBagConstraints14.anchor = GridBagConstraints.EAST;
			gridBagConstraints14.gridy = 4;
			gridBagConstraints14.gridx = 0;
			jLabel5 = new JLabel();
			jLabel5.setText("Stay in domain : ");
			GridBagConstraints gridBagConstraints13 = new GridBagConstraints();
			gridBagConstraints13.fill = GridBagConstraints.BOTH;
			gridBagConstraints13.gridy = 3;
			gridBagConstraints13.weightx = 0.0;
			gridBagConstraints13.anchor = GridBagConstraints.WEST;
			gridBagConstraints13.gridwidth = 1;
			gridBagConstraints13.gridx = 1;
			GridBagConstraints gridBagConstraints8 = new GridBagConstraints();
			gridBagConstraints8.anchor = GridBagConstraints.EAST;
			gridBagConstraints8.gridy = 3;
			gridBagConstraints8.gridx = 0;
			jLabel4 = new JLabel();
			jLabel4.setText("Maximum depth to search : ");
			GridBagConstraints gridBagConstraints7 = new GridBagConstraints();
			gridBagConstraints7.fill = GridBagConstraints.BOTH;
			gridBagConstraints7.gridy = 2;
			gridBagConstraints7.weightx = 0.0;
			gridBagConstraints7.anchor = GridBagConstraints.WEST;
			gridBagConstraints7.gridwidth = 1;
			gridBagConstraints7.gridx = 1;
			GridBagConstraints gridBagConstraints6 = new GridBagConstraints();
			gridBagConstraints6.anchor = GridBagConstraints.EAST;
			gridBagConstraints6.gridy = 2;
			gridBagConstraints6.gridx = 0;
			jLabel3 = new JLabel();
			jLabel3.setText("Maximum URL to visit : ");
			GridBagConstraints gridBagConstraints111 = new GridBagConstraints();
			gridBagConstraints111.anchor = GridBagConstraints.WEST;
			gridBagConstraints111.gridy = 1;
			gridBagConstraints111.gridx = 2;
			GridBagConstraints gridBagConstraints10 = new GridBagConstraints();
			gridBagConstraints10.anchor = GridBagConstraints.WEST;
			gridBagConstraints10.gridy = 1;
			gridBagConstraints10.gridx = 1;
			GridBagConstraints gridBagConstraints12 = new GridBagConstraints();
			gridBagConstraints12.anchor = GridBagConstraints.EAST;
			gridBagConstraints12.gridy = 1;
			gridBagConstraints12.gridx = 0;
			crawlingMethodLabel = new JLabel();
			crawlingMethodLabel.setText("Search method : ");
			optionsPanel = new JPanel();
			optionsPanel.setLayout(new GridBagLayout());
			optionsPanel.setPreferredSize(new Dimension(200, 247));
			optionsPanel.add(crawlingMethodLabel, gridBagConstraints12);
			optionsPanel.add(getJRadioButton(), gridBagConstraints10);
			optionsPanel.add(getJRadioButton1(), gridBagConstraints111);
			optionsPanel.add(jLabel3, gridBagConstraints6);
			optionsPanel.add(getJTextField(), gridBagConstraints7);
			optionsPanel.add(jLabel4, gridBagConstraints8);
			optionsPanel.add(getJTextField1(), gridBagConstraints13);
			optionsPanel.add(jLabel5, gridBagConstraints14);
			optionsPanel.add(getJRadioButton2(), gridBagConstraints15);
			optionsPanel.add(getJRadioButton3(), gridBagConstraints16);
			optionsPanel.add(getJScrollPane2(), gridBagConstraints9);
			optionsPanel.add(jLabel13, gridBagConstraints40);
			optionsPanel.add(getJToolBar1(), gridBagConstraints43);
			optionsPanel.add(getJToolBar2(), gridBagConstraints44);
			optionsPanel.add(jLabel1, gridBagConstraints);
			optionsPanel.add(getJRadioButton5(), gridBagConstraints1);
			optionsPanel.add(getJRadioButton6(), gridBagConstraints2);
			methodGroup = new ButtonGroup();
			methodGroup.add(getJRadioButton());
			methodGroup.add(getJRadioButton1());			
			stayInDomainGroup = new ButtonGroup();
			stayInDomainGroup.add(getJRadioButton2());
			stayInDomainGroup.add(getJRadioButton3());		
			countImagesGroup = new ButtonGroup();
			countImagesGroup.add(getJRadioButton5());
			countImagesGroup.add(getJRadioButton6());		
		}
		return optionsPanel;
	}

	/**
	 * This method initializes jRadioButton	
	 * 	
	 * @return javax.swing.JRadioButton	
	 */
	private JRadioButton getJRadioButton() {
		if (jRadioButton == null) {
			jRadioButton = new JRadioButton();
			jRadioButton.setText("Breadh first");
		}
		return jRadioButton;
	}

	/**
	 * This method initializes jRadioButton1	
	 * 	
	 * @return javax.swing.JRadioButton	
	 */
	private JRadioButton getJRadioButton1() {
		if (jRadioButton1 == null) {
			jRadioButton1 = new JRadioButton();
			jRadioButton1.setText("Depth first");
		}
		return jRadioButton1;
	}

	/**
	 * This method initializes jTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTextField() {
		if (jTextField == null) {
			jTextField = new JTextField();
			jTextField.setInputVerifier(new IntegerVerifier(this,false,1,1000000));
			jTextField.setPreferredSize(new Dimension(0, 0));
		}
		return jTextField;
	}

	/**
	 * This method initializes jTextField1	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTextField1() {
		if (jTextField1 == null) {
			jTextField1 = new JTextField();
			jTextField1.setInputVerifier(new IntegerVerifier(this,false,1,10000));
			jTextField1.setPreferredSize(new Dimension(0, 0));
		}
		return jTextField1;
	}

	/**
	 * This method initializes jRadioButton2	
	 * 	
	 * @return javax.swing.JRadioButton	
	 */
	private JRadioButton getJRadioButton2() {
		if (jRadioButton2 == null) {
			jRadioButton2 = new JRadioButton();
			jRadioButton2.setText("Yes");
		}
		return jRadioButton2;
	}

	/**
	 * This method initializes jRadioButton3	
	 * 	
	 * @return javax.swing.JRadioButton	
	 */
	private JRadioButton getJRadioButton3() {
		if (jRadioButton3 == null) {
			jRadioButton3 = new JRadioButton();
			jRadioButton3.setText("No");
		}
		return jRadioButton3;
	}

	/**
	 * This method initializes optionsPanel1	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getOptionsPanel1() {
		if (optionsPanel1 == null) {
			GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
			gridBagConstraints3.gridx = 0;
			gridBagConstraints3.fill = GridBagConstraints.HORIZONTAL;
			gridBagConstraints3.gridwidth = 6;
			gridBagConstraints3.ipadx = 0;
			gridBagConstraints3.ipady = 1;
			gridBagConstraints3.insets = new Insets(0, 5, 0, 5);
			gridBagConstraints3.gridy = 8;
			GridBagConstraints gridBagConstraints42 = new GridBagConstraints();
			gridBagConstraints42.fill = GridBagConstraints.HORIZONTAL;
			gridBagConstraints42.gridy = 1;
			gridBagConstraints42.weightx = 1.0;
			gridBagConstraints42.gridwidth = 6;
			gridBagConstraints42.anchor = GridBagConstraints.NORTHWEST;
			gridBagConstraints42.weighty = 0.0;
			gridBagConstraints42.ipadx = 0;
			gridBagConstraints42.gridx = 0;
			GridBagConstraints gridBagConstraints39 = new GridBagConstraints();
			gridBagConstraints39.gridx = 5;
			gridBagConstraints39.anchor = GridBagConstraints.WEST;
			gridBagConstraints39.gridy = 7;
			GridBagConstraints gridBagConstraints38 = new GridBagConstraints();
			gridBagConstraints38.gridx = 5;
			gridBagConstraints38.anchor = GridBagConstraints.WEST;
			gridBagConstraints38.gridy = 6;
			GridBagConstraints gridBagConstraints37 = new GridBagConstraints();
			gridBagConstraints37.gridx = 5;
			gridBagConstraints37.anchor = GridBagConstraints.WEST;
			gridBagConstraints37.gridy = 4;
			GridBagConstraints gridBagConstraints36 = new GridBagConstraints();
			gridBagConstraints36.gridx = 5;
			gridBagConstraints36.anchor = GridBagConstraints.WEST;
			gridBagConstraints36.gridy = 3;
			GridBagConstraints gridBagConstraints35 = new GridBagConstraints();
			gridBagConstraints35.gridx = 2;
			gridBagConstraints35.anchor = GridBagConstraints.WEST;
			gridBagConstraints35.gridy = 7;
			GridBagConstraints gridBagConstraints34 = new GridBagConstraints();
			gridBagConstraints34.gridx = 2;
			gridBagConstraints34.anchor = GridBagConstraints.WEST;
			gridBagConstraints34.gridy = 6;
			GridBagConstraints gridBagConstraints33 = new GridBagConstraints();
			gridBagConstraints33.gridx = 5;
			gridBagConstraints33.anchor = GridBagConstraints.WEST;
			gridBagConstraints33.gridy = 2;
			GridBagConstraints gridBagConstraints32 = new GridBagConstraints();
			gridBagConstraints32.gridx = 2;
			gridBagConstraints32.anchor = GridBagConstraints.WEST;
			gridBagConstraints32.gridy = 4;
			GridBagConstraints gridBagConstraints31 = new GridBagConstraints();
			gridBagConstraints31.gridx = 4;
			gridBagConstraints31.gridy = 7;
			GridBagConstraints gridBagConstraints30 = new GridBagConstraints();
			gridBagConstraints30.gridx = 4;
			gridBagConstraints30.gridy = 6;
			GridBagConstraints gridBagConstraints29 = new GridBagConstraints();
			gridBagConstraints29.gridx = 4;
			gridBagConstraints29.gridy = 4;
			GridBagConstraints gridBagConstraints28 = new GridBagConstraints();
			gridBagConstraints28.gridx = 4;
			gridBagConstraints28.gridy = 3;
			GridBagConstraints gridBagConstraints27 = new GridBagConstraints();
			gridBagConstraints27.gridx = 1;
			gridBagConstraints27.gridy = 7;
			GridBagConstraints gridBagConstraints26 = new GridBagConstraints();
			gridBagConstraints26.gridx = 1;
			gridBagConstraints26.gridy = 6;
			GridBagConstraints gridBagConstraints25 = new GridBagConstraints();
			gridBagConstraints25.gridx = 4;
			gridBagConstraints25.gridy = 2;
			GridBagConstraints gridBagConstraints24 = new GridBagConstraints();
			gridBagConstraints24.gridx = 1;
			gridBagConstraints24.gridy = 4;
			GridBagConstraints gridBagConstraints23 = new GridBagConstraints();
			gridBagConstraints23.gridx = 3;
			gridBagConstraints23.anchor = GridBagConstraints.EAST;
			gridBagConstraints23.gridy = 7;
			jLabel12 = new JLabel();
			jLabel12.setText("Show illegal arguments : ");
			GridBagConstraints gridBagConstraints22 = new GridBagConstraints();
			gridBagConstraints22.gridx = 3;
			gridBagConstraints22.anchor = GridBagConstraints.EAST;
			gridBagConstraints22.gridy = 6;
			jLabel11 = new JLabel();
			jLabel11.setText("Show encoding problems : ");
			GridBagConstraints gridBagConstraints21 = new GridBagConstraints();
			gridBagConstraints21.gridx = 3;
			gridBagConstraints21.ipadx = 0;
			gridBagConstraints21.insets = new Insets(0, 5, 0, 0);
			gridBagConstraints21.gridy = 4;
			jLabel10 = new JLabel();
			jLabel10.setText("Show skipped for depth URL : ");
			GridBagConstraints gridBagConstraints20 = new GridBagConstraints();
			gridBagConstraints20.gridx = 3;
			gridBagConstraints20.anchor = GridBagConstraints.EAST;
			gridBagConstraints20.gridy = 3;
			jLabel9 = new JLabel();
			jLabel9.setText("Show skipped URL : ");
			GridBagConstraints gridBagConstraints19 = new GridBagConstraints();
			gridBagConstraints19.gridx = 3;
			gridBagConstraints19.anchor = GridBagConstraints.EAST;
			gridBagConstraints19.gridy = 2;
			jLabel8 = new JLabel();
			jLabel8.setText("Show Visited URL : ");
			GridBagConstraints gridBagConstraints18 = new GridBagConstraints();
			gridBagConstraints18.gridx = 0;
			gridBagConstraints18.anchor = GridBagConstraints.EAST;
			gridBagConstraints18.weighty = 1.0;
			gridBagConstraints18.gridy = 7;
			jLabel7 = new JLabel();
			jLabel7.setText("show invalid URL :");
			GridBagConstraints gridBagConstraints17 = new GridBagConstraints();
			gridBagConstraints17.gridx = 0;
			gridBagConstraints17.anchor = GridBagConstraints.EAST;
			gridBagConstraints17.weighty = 1.0;
			gridBagConstraints17.gridy = 6;
			jLabel6 = new JLabel();
			jLabel6.setText("Show invalid domains :");
			GridBagConstraints gridBagConstraints11 = new GridBagConstraints();
			gridBagConstraints11.gridx = 0;
			gridBagConstraints11.anchor = GridBagConstraints.EAST;
			gridBagConstraints11.weighty = 1.0;
			gridBagConstraints11.gridy = 4;
			jLabel = new JLabel();
			jLabel.setText("Summarize crawling :");
			GridBagConstraints gridBagConstraints5 = new GridBagConstraints();
			gridBagConstraints5.gridx = 2;
			gridBagConstraints5.anchor = GridBagConstraints.WEST;
			gridBagConstraints5.gridy = 3;
			GridBagConstraints gridBagConstraints4 = new GridBagConstraints();
			gridBagConstraints4.gridx = 1;
			gridBagConstraints4.gridy = 3;
			GridBagConstraints gridBagConstraints61 = new GridBagConstraints();
			gridBagConstraints61.anchor = GridBagConstraints.EAST;
			gridBagConstraints61.gridy = 3;
			gridBagConstraints61.weighty = 1.0;
			gridBagConstraints61.gridx = 0;
			jLabel31 = new JLabel();
			jLabel31.setText("Use console : ");
			GridBagConstraints gridBagConstraints1111 = new GridBagConstraints();
			gridBagConstraints1111.anchor = GridBagConstraints.WEST;
			gridBagConstraints1111.gridy = 2;
			gridBagConstraints1111.gridx = 2;
			GridBagConstraints gridBagConstraints101 = new GridBagConstraints();
			gridBagConstraints101.anchor = GridBagConstraints.WEST;
			gridBagConstraints101.gridy = 2;
			gridBagConstraints101.gridx = 1;
			GridBagConstraints gridBagConstraints121 = new GridBagConstraints();
			gridBagConstraints121.gridy = 2;
			gridBagConstraints121.anchor = GridBagConstraints.EAST;
			gridBagConstraints121.weightx = 0.0;
			gridBagConstraints121.weighty = 1.0;
			gridBagConstraints121.gridwidth = 1;
			gridBagConstraints121.gridheight = 1;
			gridBagConstraints121.fill = GridBagConstraints.NONE;
			gridBagConstraints121.gridx = 0;
			crawlingMethodLabel1 = new JLabel();
			crawlingMethodLabel1.setText("Verbose : ");
			optionsPanel1 = new JPanel();
			optionsPanel1.setLayout(new GridBagLayout());
			optionsPanel1.setPreferredSize(new Dimension(650, 120));
			optionsPanel1.add(crawlingMethodLabel1, gridBagConstraints121);
			optionsPanel1.add(getJRadioButton4(), gridBagConstraints101);
			optionsPanel1.add(getJRadioButton11(), gridBagConstraints1111);
			optionsPanel1.add(jLabel31, gridBagConstraints61);
			optionsPanel1.add(getJRadioButton211(), gridBagConstraints4);
			optionsPanel1.add(getJRadioButton311(), gridBagConstraints5);
			optionsPanel1.add(jLabel, gridBagConstraints11);
			optionsPanel1.add(jLabel6, gridBagConstraints17);
			optionsPanel1.add(jLabel7, gridBagConstraints18);
			optionsPanel1.add(jLabel8, gridBagConstraints19);
			optionsPanel1.add(jLabel9, gridBagConstraints20);
			optionsPanel1.add(jLabel10, gridBagConstraints21);
			optionsPanel1.add(jLabel11, gridBagConstraints22);
			optionsPanel1.add(jLabel12, gridBagConstraints23);
			optionsPanel1.add(getJRadioButton2111(), gridBagConstraints24);
			optionsPanel1.add(getJRadioButton2112(), gridBagConstraints25);
			optionsPanel1.add(getJRadioButton2113(), gridBagConstraints26);
			optionsPanel1.add(getJRadioButton2114(), gridBagConstraints27);
			optionsPanel1.add(getJRadioButton2115(), gridBagConstraints28);
			optionsPanel1.add(getJRadioButton2116(), gridBagConstraints29);
			optionsPanel1.add(getJRadioButton2117(), gridBagConstraints30);
			optionsPanel1.add(getJRadioButton2118(), gridBagConstraints31);
			optionsPanel1.add(getJRadioButton3111(), gridBagConstraints32);
			optionsPanel1.add(getJRadioButton3112(), gridBagConstraints33);
			optionsPanel1.add(getJRadioButton3113(), gridBagConstraints34);
			optionsPanel1.add(getJRadioButton3114(), gridBagConstraints35);
			optionsPanel1.add(getJRadioButton3115(), gridBagConstraints36);
			optionsPanel1.add(getJRadioButton3116(), gridBagConstraints37);
			optionsPanel1.add(getJRadioButton3117(), gridBagConstraints38);
			optionsPanel1.add(getJRadioButton3118(), gridBagConstraints39);
			optionsPanel1.add(getJToolBar(), gridBagConstraints42);
			optionsPanel1.add(errorLabel, gridBagConstraints3);
			
			verboseGroup.add(getJRadioButton4());
			verboseGroup.add(getJRadioButton11());			
			useConsoleGroup.add(getJRadioButton211());
			useConsoleGroup.add(getJRadioButton311());			
			summarizeCrawlingGroup.add(getJRadioButton2111());
			summarizeCrawlingGroup.add(getJRadioButton3111());			
			showInvalidDomainsGroup.add(getJRadioButton2113());
			showInvalidDomainsGroup.add(getJRadioButton3113());			
			showInvalidURLGroup.add(getJRadioButton2114());
			showInvalidURLGroup.add(getJRadioButton3114());			
			showVisitedURLGroup.add(getJRadioButton2112());
			showVisitedURLGroup.add(getJRadioButton3112());			
			showSkippedURLGroup.add(getJRadioButton2115());
			showSkippedURLGroup.add(getJRadioButton3115());			
			showSkippedForDepthURLGroup.add(getJRadioButton2116());
			showSkippedForDepthURLGroup.add(getJRadioButton3116());			
			showEncodingProblemsGroup.add(getJRadioButton2117());
			showEncodingProblemsGroup.add(getJRadioButton3117());			
			showIllegalArgumentsGroup.add(getJRadioButton2118());
			showIllegalArgumentsGroup.add(getJRadioButton3118());			
		}
		return optionsPanel1;
	}

	/**
	 * This method initializes jRadioButton4	
	 * 	
	 * @return javax.swing.JRadioButton	
	 */
	private JRadioButton getJRadioButton4() {
		if (jRadioButton4 == null) {
			jRadioButton4 = new JRadioButton();
			jRadioButton4.setText("Yes");
		}
		return jRadioButton4;
	}

	/**
	 * This method initializes jRadioButton11	
	 * 	
	 * @return javax.swing.JRadioButton	
	 */
	private JRadioButton getJRadioButton11() {
		if (jRadioButton11 == null) {
			jRadioButton11 = new JRadioButton();
			jRadioButton11.setText("No");
		}
		return jRadioButton11;
	}

	/**
	 * This method initializes jRadioButton211	
	 * 	
	 * @return javax.swing.JRadioButton	
	 */
	private JRadioButton getJRadioButton211() {
		if (jRadioButton211 == null) {
			jRadioButton211 = new JRadioButton();
			jRadioButton211.setText("Yes");
		}
		return jRadioButton211;
	}

	/**
	 * This method initializes jRadioButton311	
	 * 	
	 * @return javax.swing.JRadioButton	
	 */
	private JRadioButton getJRadioButton311() {
		if (jRadioButton311 == null) {
			jRadioButton311 = new JRadioButton();
			jRadioButton311.setText("No");
		}
		return jRadioButton311;
	}

	/**
	 * This method initializes jScrollPane2	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane2() {
		if (jScrollPane2 == null) {
			jScrollPane2 = new JScrollPane();
			jScrollPane2.setPreferredSize(new Dimension(0, 0));
			jScrollPane2.setViewportView(getJTextArea2());
		}
		return jScrollPane2;
	}

	/**
	 * This method initializes jTextArea2	
	 * 	
	 * @return javax.swing.JTextArea	
	 */
	private JTextArea getJTextArea2() {
		if (jTextArea2 == null) {
			jTextArea2 = new JTextArea();
			jTextArea2.setSize(new Dimension(306, 29));
		}
		return jTextArea2;
	}

	/**
	 * This method initializes jRadioButton2111	
	 * 	
	 * @return javax.swing.JRadioButton	
	 */
	private JRadioButton getJRadioButton2111() {
		if (jRadioButton2111 == null) {
			jRadioButton2111 = new JRadioButton();
			jRadioButton2111.setText("Yes");
		}
		return jRadioButton2111;
	}

	/**
	 * This method initializes jRadioButton2112	
	 * 	
	 * @return javax.swing.JRadioButton	
	 */
	private JRadioButton getJRadioButton2112() {
		if (jRadioButton2112 == null) {
			jRadioButton2112 = new JRadioButton();
			jRadioButton2112.setText("Yes");
		}
		return jRadioButton2112;
	}

	/**
	 * This method initializes jRadioButton2113	
	 * 	
	 * @return javax.swing.JRadioButton	
	 */
	private JRadioButton getJRadioButton2113() {
		if (jRadioButton2113 == null) {
			jRadioButton2113 = new JRadioButton();
			jRadioButton2113.setText("Yes");
		}
		return jRadioButton2113;
	}

	/**
	 * This method initializes jRadioButton2114	
	 * 	
	 * @return javax.swing.JRadioButton	
	 */
	private JRadioButton getJRadioButton2114() {
		if (jRadioButton2114 == null) {
			jRadioButton2114 = new JRadioButton();
			jRadioButton2114.setText("Yes");
		}
		return jRadioButton2114;
	}

	/**
	 * This method initializes jRadioButton2115	
	 * 	
	 * @return javax.swing.JRadioButton	
	 */
	private JRadioButton getJRadioButton2115() {
		if (jRadioButton2115 == null) {
			jRadioButton2115 = new JRadioButton();
			jRadioButton2115.setText("Yes");
		}
		return jRadioButton2115;
	}

	/**
	 * This method initializes jRadioButton2116	
	 * 	
	 * @return javax.swing.JRadioButton	
	 */
	private JRadioButton getJRadioButton2116() {
		if (jRadioButton2116 == null) {
			jRadioButton2116 = new JRadioButton();
			jRadioButton2116.setText("Yes");
		}
		return jRadioButton2116;
	}

	/**
	 * This method initializes jRadioButton2117	
	 * 	
	 * @return javax.swing.JRadioButton	
	 */
	private JRadioButton getJRadioButton2117() {
		if (jRadioButton2117 == null) {
			jRadioButton2117 = new JRadioButton();
			jRadioButton2117.setText("Yes");
		}
		return jRadioButton2117;
	}

	/**
	 * This method initializes jRadioButton2118	
	 * 	
	 * @return javax.swing.JRadioButton	
	 */
	private JRadioButton getJRadioButton2118() {
		if (jRadioButton2118 == null) {
			jRadioButton2118 = new JRadioButton();
			jRadioButton2118.setText("Yes");
		}
		return jRadioButton2118;
	}

	/**
	 * This method initializes jRadioButton3111	
	 * 	
	 * @return javax.swing.JRadioButton	
	 */
	private JRadioButton getJRadioButton3111() {
		if (jRadioButton3111 == null) {
			jRadioButton3111 = new JRadioButton();
			jRadioButton3111.setText("No");
		}
		return jRadioButton3111;
	}

	/**
	 * This method initializes jRadioButton3112	
	 * 	
	 * @return javax.swing.JRadioButton	
	 */
	private JRadioButton getJRadioButton3112() {
		if (jRadioButton3112 == null) {
			jRadioButton3112 = new JRadioButton();
			jRadioButton3112.setText("No");
		}
		return jRadioButton3112;
	}

	/**
	 * This method initializes jRadioButton3113	
	 * 	
	 * @return javax.swing.JRadioButton	
	 */
	private JRadioButton getJRadioButton3113() {
		if (jRadioButton3113 == null) {
			jRadioButton3113 = new JRadioButton();
			jRadioButton3113.setText("No");
		}
		return jRadioButton3113;
	}

	/**
	 * This method initializes jRadioButton3114	
	 * 	
	 * @return javax.swing.JRadioButton	
	 */
	private JRadioButton getJRadioButton3114() {
		if (jRadioButton3114 == null) {
			jRadioButton3114 = new JRadioButton();
			jRadioButton3114.setText("No");
		}
		return jRadioButton3114;
	}

	/**
	 * This method initializes jRadioButton3115	
	 * 	
	 * @return javax.swing.JRadioButton	
	 */
	private JRadioButton getJRadioButton3115() {
		if (jRadioButton3115 == null) {
			jRadioButton3115 = new JRadioButton();
			jRadioButton3115.setText("No");
		}
		return jRadioButton3115;
	}

	/**
	 * This method initializes jRadioButton3116	
	 * 	
	 * @return javax.swing.JRadioButton	
	 */
	private JRadioButton getJRadioButton3116() {
		if (jRadioButton3116 == null) {
			jRadioButton3116 = new JRadioButton();
			jRadioButton3116.setText("No");
		}
		return jRadioButton3116;
	}

	/**
	 * This method initializes jRadioButton3117	
	 * 	
	 * @return javax.swing.JRadioButton	
	 */
	private JRadioButton getJRadioButton3117() {
		if (jRadioButton3117 == null) {
			jRadioButton3117 = new JRadioButton();
			jRadioButton3117.setText("No");
		}
		return jRadioButton3117;
	}

	/**
	 * This method initializes jRadioButton3118	
	 * 	
	 * @return javax.swing.JRadioButton	
	 */
	private JRadioButton getJRadioButton3118() {
		if (jRadioButton3118 == null) {
			jRadioButton3118 = new JRadioButton();
			jRadioButton3118.setText("No");
		}
		return jRadioButton3118;
	}

	/**
	 * This method initializes jToolBar	
	 * 	
	 * @return javax.swing.JToolBar	
	 */
	private JToolBar getJToolBar() {
		if (jToolBar == null) {
			jLabel15 = new JLabel();
			jLabel15.setText("Verbosity preset : ");
			jToolBar = new JToolBar();
			jToolBar.add(jLabel15);
			jToolBar.add(getJButton2());
			jToolBar.add(getJButton());
			jToolBar.add(getJButton1());
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
			jButton.setText("Full");
			jButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					setFullVerbosity(true);
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
			jButton1.setText("None");
			jButton1.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					setFullVerbosity(false);
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
			jButton2.setText("Default");
			jButton2.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					setDefaultVerbosity();
				}
			});
		}
		return jButton2;
	}

	/**
	 * This method initializes jToolBar1	
	 * 	
	 * @return javax.swing.JToolBar	
	 */
	private JToolBar getJToolBar1() {
		if (jToolBar1 == null) {
			jLabel14 = new JLabel();
			jLabel14.setText("Search preset : ");
			jToolBar1 = new JToolBar();
			jToolBar1.add(jLabel14);
			jToolBar1.add(getJButton7());
			jToolBar1.add(getJButton3());
			jToolBar1.add(getJButton4());
			jToolBar1.add(getJButton5());
			jToolBar1.add(getJButton6());
			jToolBar1.add(getJButton8());
		}
		return jToolBar1;
	}

	/**
	 * This method initializes jButton3	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButton3() {
		if (jButton3 == null) {
			jButton3 = new JButton();
			jButton3.setText("Small");
			jButton3.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					setCrawlingStyle(SITESIZE.SMALL);
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
			jButton4.setText("Middle");
			jButton4.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					setCrawlingStyle(SITESIZE.MIDDLE);
				}
			});
		}
		return jButton4;
	}

	/**
	 * This method initializes jButton5	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButton5() {
		if (jButton5 == null) {
			jButton5 = new JButton();
			jButton5.setText("Big");
			jButton5.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					setCrawlingStyle(SITESIZE.BIG);
				}
			});
		}
		return jButton5;
	}

	/**
	 * This method initializes jButton6	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButton6() {
		if (jButton6 == null) {
			jButton6 = new JButton();
			jButton6.setText("Huge");
			jButton6.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					setCrawlingStyle(SITESIZE.HUGE);
				}
			});
		}
		return jButton6;
	}

	/**
	 * This method initializes jButton7	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButton7() {
		if (jButton7 == null) {
			jButton7 = new JButton();
			jButton7.setText("Default");
			jButton7.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					setCrawlingStyle(SITESIZE.DEFAULT);
				}
			});
		}
		return jButton7;
	}

	/**
	 * This method initializes jButton8	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButton8() {
		if (jButton8 == null) {
			jButton8 = new JButton();
			jButton8.setText("Infinite");
			jButton8.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					setCrawlingStyle(SITESIZE.INFINITE);
				}
			});
		}
		return jButton8;
	}

	/**
	 * This method initializes jToolBar2	
	 * 	
	 * @return javax.swing.JToolBar	
	 */
	private JToolBar getJToolBar2() {
		if (jToolBar2 == null) {
			jLabel16 = new JLabel();
			jLabel16.setText("Extensions preset : ");
			jToolBar2 = new JToolBar();
			jToolBar2.add(jLabel16);
			jToolBar2.add(getJButton9());
			jToolBar2.add(getJButton10());
			jToolBar2.add(getJButton11());
		}
		return jToolBar2;
	}

	/**
	 * This method initializes jButton9	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButton9() {
		if (jButton9 == null) {
			jButton9 = new JButton();
			jButton9.setText("Full");
			jButton9.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					setExtensions(Constants.EXTENSIONS_STYLE.FULL);
				}
			});
		}
		return jButton9;
	}

	/**
	 * This method initializes jButton10	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButton10() {
		if (jButton10 == null) {
			jButton10 = new JButton();
			jButton10.setText("Static");
			jButton10.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					setExtensions(Constants.EXTENSIONS_STYLE.STATIC);
				}
			});
		}
		return jButton10;
	}

	/**
	 * This method initializes jButton11	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButton11() {
		if (jButton11 == null) {
			jButton11 = new JButton();
			jButton11.setText("Dynamic");
			jButton11.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					setExtensions(Constants.EXTENSIONS_STYLE.DYNAMIC);
				}
			});
		}
		return jButton11;
	}

	/**
	 * This method initializes jRadioButton5	
	 * 	
	 * @return javax.swing.JRadioButton	
	 */
	private JRadioButton getJRadioButton5() {
		if (jRadioButton5 == null) {
			jRadioButton5 = new JRadioButton();
			jRadioButton5.setText("Yes");
		}
		return jRadioButton5;
	}

	/**
	 * This method initializes jRadioButton6	
	 * 	
	 * @return javax.swing.JRadioButton	
	 */
	private JRadioButton getJRadioButton6() {
		if (jRadioButton6 == null) {
			jRadioButton6 = new JRadioButton();
			jRadioButton6.setText("No");
		}
		return jRadioButton6;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				ParametersPanel thisClass = new ParametersPanel(null);
				//thisClass.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				thisClass.setVisible(true);
			}
		});
	}

	/**
	 * This is the default constructor
	 */
	private JButton startButton;
	public ParametersPanel(JButton startButton) {
		super();
		setBorder(null);
		initialize();
		setVisible(true);
		this.startButton = startButton;
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(new Dimension(512, 557));
		this.add(getJContentPane());
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(new BoxLayout(getJContentPane(), BoxLayout.Y_AXIS));
			jContentPane.setPreferredSize(new Dimension(500, 547));
			jContentPane.add(getJPanel(), null);
		}
		return jContentPane;
	}

	/**
	 * ParametersPanel - SpiderView communication
	 */
	ButtonGroup methodGroup = new ButtonGroup();  //  @jve:decl-index=0:
	ButtonGroup stayInDomainGroup = new ButtonGroup();  //  @jve:decl-index=0:
	ButtonGroup countImagesGroup = new ButtonGroup();  //  @jve:decl-index=0:
	ButtonGroup verboseGroup = new ButtonGroup();  //  @jve:decl-index=0:
	ButtonGroup useConsoleGroup = new ButtonGroup();  //  @jve:decl-index=0:
	ButtonGroup summarizeCrawlingGroup = new ButtonGroup();  //  @jve:decl-index=0:
	ButtonGroup showInvalidDomainsGroup = new ButtonGroup();
	ButtonGroup showInvalidURLGroup = new ButtonGroup();  //  @jve:decl-index=0:
	ButtonGroup showVisitedURLGroup = new ButtonGroup();  //  @jve:decl-index=0:
	ButtonGroup showSkippedURLGroup = new ButtonGroup();
	ButtonGroup showSkippedForDepthURLGroup = new ButtonGroup();  //  @jve:decl-index=0:
	ButtonGroup showEncodingProblemsGroup = new ButtonGroup();  //  @jve:decl-index=0:
	ButtonGroup showIllegalArgumentsGroup = new ButtonGroup();  //  @jve:decl-index=0:
	

	public METHOD getMethod() { 
		return methodGroup.isSelected(jRadioButton.getModel()) ? METHOD.BREADTH_FIRST : METHOD.DEPTH_FIRST; 
	}
	public String getFieldMaxURLtoVisit() { 
		return jTextField.getText(); 
	}
	public Integer getMaxURLtoVisit() { 
		return Integer.parseInt(jTextField.getText()); 
	}
	public String getFieldMaxDepth() { 
		return jTextField1.getText(); 
	}
	public Integer getMaxDepth() { 
		return Integer.parseInt(jTextField1.getText()); 
	}
	public boolean getStayInDomain() { 
		return stayInDomainGroup.isSelected(jRadioButton2.getModel()) ? true : false; 
	}
	public boolean getCountImages() { 
		return countImagesGroup.isSelected(jRadioButton5.getModel()) ? true : false; 
	}
	public String getAllowedExtensions() { 
		return jTextArea2.getText(); 
	}	
	public boolean getVerbose() { 
		return verboseGroup.isSelected(jRadioButton4.getModel()) ? true : false; 
	}
	public boolean getUseConsole() { 
		return useConsoleGroup.isSelected(jRadioButton211.getModel()) ? true : false; 
	}
	public boolean getSummarizeCrawling() { 
		return summarizeCrawlingGroup.isSelected(jRadioButton2111.getModel()) ? true : false; 
	}
	public boolean getShowInvalidDomains() { 
		return showInvalidDomainsGroup.isSelected(jRadioButton2113.getModel()) ? true : false; 
	}
	public boolean getShowInvalidURL() { 
		return showInvalidURLGroup.isSelected(jRadioButton2114.getModel()) ? true : false; 
	}
	public boolean getShowVisitedURL() { 
		return showVisitedURLGroup.isSelected(jRadioButton2112.getModel()) ? true : false; 
	}
	public boolean getShowSkippedURL() { 
		return showSkippedURLGroup.isSelected(jRadioButton2115.getModel()) ? true : false; 
	}
	public boolean getShowSkippedForDepthURL() { 
		return showSkippedForDepthURLGroup.isSelected(jRadioButton2116.getModel()) ? true : false; 
	}
	public boolean getShowEncodingProblems() { 
		return showEncodingProblemsGroup.isSelected(jRadioButton2117.getModel()) ? true : false; 
	}
	public boolean getShowIllegalArguments() { 
		return showIllegalArgumentsGroup.isSelected(jRadioButton2118.getModel()) ? true : false; 
	}

	public void setParameters(Profile parameters) {
		// url and parsing options
		jTextArea2.setText("");
		for (String extension : parameters.getAllowedExtensions())
			if (!extension.trim().equals("")) {
				String ext = extension.trim().toLowerCase();
				jTextArea2.append((ext.startsWith(".") ? ext : "."+ext)+"\n");
			}
		// crawling options
		methodGroup.setSelected(parameters.getMethod() == METHOD.BREADTH_FIRST ? 
				jRadioButton.getModel() : jRadioButton1.getModel(), true);
		jTextField.setText(parameters.getMaxURLtoVisit().toString());
		jTextField1.setText(parameters.getMaxDepth().toString());
		stayInDomainGroup.setSelected(parameters.getStayInDomain() ? jRadioButton2.getModel() :
			jRadioButton3.getModel(), true);
		countImagesGroup.setSelected(parameters.getImagesCount() ? jRadioButton5.getModel() :
			jRadioButton6.getModel(), true);
		// verbosity options
		verboseGroup.setSelected(parameters.getVerbose() ? 
				jRadioButton4.getModel() : jRadioButton11.getModel(), true);
		useConsoleGroup.setSelected(parameters.getUseConsole() ? 
				jRadioButton211.getModel() : jRadioButton311.getModel(), true);
		summarizeCrawlingGroup.setSelected(parameters.getSummarizeCrawling() ? 
				jRadioButton2111.getModel() : jRadioButton3111.getModel(), true);
		showInvalidDomainsGroup.setSelected(parameters.getShowInvalidDomains() ? 
				jRadioButton2113.getModel() : jRadioButton3113.getModel(), true);
		showInvalidURLGroup.setSelected(parameters.getShowInvalidURL() ? 
				jRadioButton2114.getModel() : jRadioButton3114.getModel(), true);
		showVisitedURLGroup.setSelected(parameters.getShowVisitedURL() ? 
				jRadioButton2112.getModel() : jRadioButton3112.getModel(), true);
		showSkippedURLGroup.setSelected(parameters.getShowSkippedURL() ? 
				jRadioButton2115.getModel() : jRadioButton3115.getModel(), true);
		showSkippedForDepthURLGroup.setSelected(parameters.getShowSkippedForDepthURL() ? 
				jRadioButton2116.getModel() : jRadioButton3116.getModel(), true);
		showEncodingProblemsGroup.setSelected(parameters.getShowEncodingProblems() ? 
				jRadioButton2117.getModel() : jRadioButton3117.getModel(), true);
		showIllegalArgumentsGroup.setSelected(parameters.getShowIllegalArguments() ? 
				jRadioButton2118.getModel() : jRadioButton3118.getModel(), true);
	}
	
	private void setCrawlingStyle(SITESIZE size) {
		// set crawling options
		Constants.SitesizeModel model = new Constants.SitesizeModel(size);
		methodGroup.setSelected(model.getMethod() == METHOD.BREADTH_FIRST ? 
				jRadioButton.getModel() : jRadioButton1.getModel(), true);
		jTextField.setText(model.getMaxURLtoVisit().toString());
		jTextField1.setText(model.getMaxDepth().toString());
		stayInDomainGroup.setSelected(model.getStayInDomain() ? jRadioButton2.getModel() :
			jRadioButton3.getModel(), true);
		countImagesGroup.setSelected(model.getCountImages() ? jRadioButton5.getModel() :
			jRadioButton6.getModel(), true);
		
	}
	private void setExtensions(Constants.EXTENSIONS_STYLE style) {
		jTextArea2.setText("");
		switch (style) {
		case FULL: 
			for (String extension : Constants.DEFAULT_FULL_EXTENSIONS) 
				if (!extension.trim().equals("")) jTextArea2.append(extension+"\n");
			break;
		case STATIC: 
			for (String extension : Constants.DEFAULT_STATIC_EXTENSIONS) 
				if (!extension.trim().equals("")) jTextArea2.append(extension+"\n");
			break;
		case DYNAMIC:
			for (String extension : Constants.DEFAULT_DYNAMIC_EXTENSIONS) 
				if (!extension.trim().equals("")) jTextArea2.append(extension+"\n");
			break;
		}
	}
	
	private void setFullVerbosity(Boolean verbose) {
		verboseGroup.setSelected(verbose ? 
				jRadioButton4.getModel() : jRadioButton11.getModel(), true);
		useConsoleGroup.setSelected(verbose ? 
				jRadioButton211.getModel() : jRadioButton311.getModel(), true);
		summarizeCrawlingGroup.setSelected(verbose ? 
				jRadioButton2111.getModel() : jRadioButton3111.getModel(), true);
		showInvalidDomainsGroup.setSelected(verbose ? 
				jRadioButton2113.getModel() : jRadioButton3113.getModel(), true);
		showInvalidURLGroup.setSelected(verbose ? 
				jRadioButton2114.getModel() : jRadioButton3114.getModel(), true);
		showVisitedURLGroup.setSelected(verbose ? 
				jRadioButton2112.getModel() : jRadioButton3112.getModel(), true);
		showSkippedURLGroup.setSelected(verbose ? 
				jRadioButton2115.getModel() : jRadioButton3115.getModel(), true);
		showSkippedForDepthURLGroup.setSelected(verbose ? 
				jRadioButton2116.getModel() : jRadioButton3116.getModel(), true);
		showEncodingProblemsGroup.setSelected(verbose ? 
				jRadioButton2117.getModel() : jRadioButton3117.getModel(), true);
		showIllegalArgumentsGroup.setSelected(verbose ? 
				jRadioButton2118.getModel() : jRadioButton3118.getModel(), true);
	}
	
	private void setDefaultVerbosity() {
		verboseGroup.setSelected(Constants.DEFAULT_VERBOSE ? 
				jRadioButton4.getModel() : jRadioButton11.getModel(), true);
		useConsoleGroup.setSelected(Constants.DEFAULT_USE_CONSOLE ? 
				jRadioButton211.getModel() : jRadioButton311.getModel(), true);
		summarizeCrawlingGroup.setSelected(Constants.DEFAULT_SUMMARIZE_CRAWLING ? 
				jRadioButton2111.getModel() : jRadioButton3111.getModel(), true);
		showInvalidDomainsGroup.setSelected(Constants.DEFAULT_SHOW_INVALID_DOMAINS ? 
				jRadioButton2113.getModel() : jRadioButton3113.getModel(), true);
		showInvalidURLGroup.setSelected(Constants.DEFAULT_SHOW_INVALID_URL ? 
				jRadioButton2114.getModel() : jRadioButton3114.getModel(), true);
		showVisitedURLGroup.setSelected(Constants.DEFAULT_SHOW_VISITED_URL ? 
				jRadioButton2112.getModel() : jRadioButton3112.getModel(), true);
		showSkippedURLGroup.setSelected(Constants.DEFAULT_SHOW_SKIPPED_URL ? 
				jRadioButton2115.getModel() : jRadioButton3115.getModel(), true);
		showSkippedForDepthURLGroup.setSelected(Constants.DEFAULT_SHOW_SKIPPED_FOR_DEPTH_URL ? 
				jRadioButton2116.getModel() : jRadioButton3116.getModel(), true);
		showEncodingProblemsGroup.setSelected(Constants.DEFAULT_SHOW_ENCODING_PROBLEMS ? 
				jRadioButton2117.getModel() : jRadioButton3117.getModel(), true);
		showIllegalArgumentsGroup.setSelected(Constants.DEFAULT_SHOW_ILLEGAL_ARGUMENTS ? 
				jRadioButton2118.getModel() : jRadioButton3118.getModel(), true);
	}
	 /**
	  * Verifier listener routine used to report bad data
	  *  @param message error message
	  *  @param jComponent component that caused the error.
	  */
	    public void invalidData(String message, JComponent jComponent) {
	        errorLabel.setText(message);
	        errorLabel.setForeground(Color.red);
	        startButton.setEnabled(false);  // turn off the start button
	        getToolkit().beep();
	    }
	  /**
	  * Verifier listener routine used to report good data
	  *  @param jComponent component that has tested ok
	  */  
	    public void validData(JComponent jComponent) {
	        startButton.setEnabled(true);  // turn on the start button
	        errorLabel.setText("  ");
	    }
	    
}  //  @jve:decl-index=0:visual-constraint="-7,2"
