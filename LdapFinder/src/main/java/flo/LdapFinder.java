package flo;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.URL;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.sun.java.swing.plaf.windows.WindowsLookAndFeel;

import flo.actions.DirectoryListMouseAction;
import flo.actions.SearchAction;

public class LdapFinder extends JPanel {

  private static final long serialVersionUID = 1L;
  private JList entries;
  
  
  private DefaultListModel model;
  
  private JPanel searchPanel;
  private JComboBox box ;
  private JTextField searchFld;
  
  
  public LdapFinder() {
    setLayout(new BorderLayout());

    model = new DefaultListModel();
    
    entries = new JList(model);
    entries.setCellRenderer(new DirectoryCellRenderer());
    entries.setVisibleRowCount(6);
    JScrollPane pane = new JScrollPane(entries);
    pane.setBorder(BorderFactory.createLineBorder(Color.gray, 1));
    
    DirectoryListMouseAction listMouseAction = new DirectoryListMouseAction(entries);
    
    entries.addMouseListener(listMouseAction);
    
    searchPanel = new JPanel();
    
    String[] values = {"Nom", "Identifiant", "Numéro de téléphone", "Bureau"};
    
    box = new JComboBox(values);
    
    box.setPreferredSize(new Dimension(150, 20));
    box.setBorder(BorderFactory.createLineBorder(Color.gray, 1));
    searchPanel.add(box);
    
    JLabel containsLbl = new JLabel("contient");
    searchPanel.add(containsLbl);
    
    searchFld = new JTextField();
    searchFld.setPreferredSize(new Dimension(150, 20));
    searchFld.setBorder(BorderFactory.createLineBorder(Color.gray, 1));
   
    searchPanel.add(searchFld);
    
    
    SearchAction searchAction = new SearchAction(this);
    
    final JButton searchBtn = new JButton("Search !");
    searchBtn.addActionListener(searchAction);
    URL iconUrl = this.getClass().getClassLoader().getResource("magnifier.png");
   
    searchBtn.setIcon(new ImageIcon(iconUrl));
    searchBtn.setBorder(BorderFactory.createLineBorder(Color.gray, 1));
    searchBtn.setPreferredSize(new Dimension(100, 20));
    searchPanel.add(searchBtn);
    
    
    searchFld.addKeyListener(new KeyListener() {
		
		public void keyTyped(KeyEvent e) {}
		public void keyReleased(KeyEvent e) {}
		public void keyPressed(KeyEvent e) {
			if(e.getKeyCode() == 10) {     
				searchBtn.requestFocus();
				// searchBtn.re
				
			}
		}
	});
    
    add(pane, BorderLayout.CENTER);
    add(searchPanel, BorderLayout.NORTH);

  }
  
  
  public void setEntries(List<DirectoryEntry> entries) {
	 model.removeAllElements();
	 for(DirectoryEntry entry : entries) {
		 model.addElement(entry);
	 }
  }

  public String getSearchField() {
	  return (String)box.getSelectedItem();
  }
  
  public String getSearchValue() {
	  return searchFld.getText();
  }

  public static void main(String...args) {

	 /* System.setProperty( "swing.aatext", "true" );
	  try {
		  UIManager.setLookAndFeel(new WindowsLookAndFeel());
	  } catch (UnsupportedLookAndFeelException e) {
		  e.printStackTrace();
	  }
*/  
	  
	  SwingUtilities.invokeLater(new Runnable() {

		  public void run() {
			  JFrame frame = new JFrame("Annuaire LDAP");
			  frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			  frame.setContentPane(new LdapFinder());

			  frame.setSize(500,400);
			  frame.setLocationRelativeTo(null);
			  frame.setVisible(true);

			  URL urlIcon = LdapFinder.class.getClassLoader().getResource("group.png");
			  frame.setIconImage(new ImageIcon(urlIcon).getImage());

		  }
	  });

  }
 
}


