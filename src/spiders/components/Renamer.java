package spiders.components;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Point;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

public class Renamer extends JDialog {

	private static final long serialVersionUID = 1L;

	private JPanel jContentPane = null;
	private JList<String> jList = null;
	private JLabel jLabel = null;
	private JPanel jPanel = null;
	private JTextField jTextField = null;
	private JPanel jPanel1 = null;
	private JButton jButton = null;
	private JButton jButton1 = null;
	private JLabel jLabel1 = null;
	private JPanel jPanel2 = null;
	
	private Frame owner;
	private String type;
	private String name;
	private Vector<String> nameList;

	private JScrollPane jScrollPane = null;

	public String getName() { return name; }

	/**
	 * @param owner
	 */
	public Renamer(Frame owner, String type, String name, Vector<String> nameList) {
		super(owner);
		this.owner = owner;
		this.type = type.toUpperCase().charAt(0) + type.toLowerCase().substring(1); // To capital format
		this.name = name;
		this.nameList = nameList;
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(449, 243);
		this.setTitle("Name already exists !");
		this.setModal(true);
		this.setContentPane(getJContentPane());
		jList.setListData(nameList);
		jTextField.setText(name);
		jLabel.setText(type+" "+name+" already exists.\nPlease, choose another one...");
		jLabel1.setText("New "+type.toLowerCase()+" :");
		name = null; // prevent OnClose
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jLabel = new JLabel();
			jLabel.setForeground(Color.red);
			jLabel.setIcon(UIManager.getIcon("OptionPane.errorIcon"));
			jLabel.setText("message");
			jContentPane = new JPanel();
			jContentPane.setLayout(new BorderLayout());
			jContentPane.add(jLabel, BorderLayout.NORTH);
			jContentPane.add(getJScrollPane(), BorderLayout.CENTER);
			jContentPane.add(getJPanel(), BorderLayout.SOUTH);
		}
		return jContentPane;
	}

	/**
	 * This method initializes jList	
	 * 	
	 * @return javax.swing.JList	
	 */
	private JList<String> getJList() {
		if (jList == null) {
			jList = new JList<String>();
			jList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
				public void valueChanged(javax.swing.event.ListSelectionEvent e) {
					jTextField.setText((String)jList.getSelectedValue());
				}
			});
		}
		return jList;
	}

	/**
	 * This method initializes jPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel() {
		if (jPanel == null) {
			jLabel1 = new JLabel();
			jLabel1.setText("New name :");
			jLabel1.setHorizontalTextPosition(SwingConstants.CENTER);
			jLabel1.setHorizontalAlignment(SwingConstants.CENTER);
			jPanel = new JPanel();
			jPanel.setLayout(new BoxLayout(getJPanel(), BoxLayout.Y_AXIS));
			jPanel.add(getJPanel2(), null);
			jPanel.add(getJTextField(), null);
			jPanel.add(getJPanel1(), null);
		}
		return jPanel;
	}

	/**
	 * This method initializes jTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTextField() {
		if (jTextField == null) {
			jTextField = new JTextField();
		}
		return jTextField;
	}

	/**
	 * This method initializes jPanel1	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel1() {
		if (jPanel1 == null) {
			jPanel1 = new JPanel();
			jPanel1.setLayout(new FlowLayout());
			jPanel1.add(getJButton(), null);
			jPanel1.add(getJButton1(), null);
		}
		return jPanel1;
	}

	/**
	 * This method initializes jButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButton() {
		if (jButton == null) {
			jButton = new JButton();
			jButton.setText("ok");
			jButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					confirm();
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
			jButton1.setText("cancel");
			jButton1.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					cancel();
				}
			});
		}
		return jButton1;
	}

	/**
	 * This method initializes jPanel2	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel2() {
		if (jPanel2 == null) {
			jPanel2 = new JPanel();
			jPanel2.setLayout(new GridBagLayout());
			jPanel2.add(jLabel1, new GridBagConstraints());
		}
		return jPanel2;
	}
	
	/**
	 * This method initializes jScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane() {
		if (jScrollPane == null) {
			jScrollPane = new JScrollPane();
			jScrollPane.setViewportView(getJList());
		}
		return jScrollPane;
	}
	
	@Override
	public void setVisible(boolean flag) {
		if (flag) centerOnOwner();
		super.setVisible(flag);
	}
	
	private void centerOnOwner() {
		Dimension size = getSize();
		Dimension ownersize = owner.getSize();
		Point point = owner.getLocationOnScreen();
		setLocation(
				Math.min(point.x + (ownersize.width - size.width)/2, owner.getToolkit().getScreenSize().width - size.width),
				Math.min(point.y + (ownersize.height - size.height)/2, owner.getToolkit().getScreenSize().height - size.height)); 		
	}
	
	private void confirm() {
		String name;
		// check for empty name
		if ((name = jTextField.getText()) == null || (name = name.trim()).equals(""))
			JOptionPane.showMessageDialog(this, "Name cannot be empty");	
		else {
		// check if name already exists in nameList
			for (String profileName : nameList)
				if (name.equalsIgnoreCase(profileName)) {
					JOptionPane.showMessageDialog(this, "Name already exists");
					return;
				}
			this.name = name;
			setVisible(false);
		}
	}
	
	private void cancel() {
		setVisible(false);
	}
}  //  @jve:decl-index=0:visual-constraint="10,10"
