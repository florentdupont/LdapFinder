package flo;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.sun.java.swing.plaf.windows.WindowsLookAndFeel;

public class DirectoryEntryView extends JFrame {

	private static DirectoryEntryView instance;
	
	
	DirectoryEntry currentEntry;
	
	
	public static DirectoryEntryView getInstance() {
		if(instance == null)
			instance = new DirectoryEntryView();
		return instance;
	}
	
	private DirectoryEntryView() {
		
		setSize(300, 200);
		
		setLocationRelativeTo(null);
	}
	
	
	
	public void setCurrentEntry(DirectoryEntry entry) {
		this.currentEntry = entry;
		initComponents();
	}
	
	private void initComponents() {
		
		setTitle(currentEntry.getName());
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		URL urlIcon = LdapFinder.class.getClassLoader().getResource("group.png");
		setIconImage(new ImageIcon(urlIcon).getImage());
		
		JPanel pane = (JPanel)getContentPane();//new JPanel();
		pane.removeAll();
		
		pane.setBackground(Color.white);
		setContentPane(pane);
		setLayout(null);
		JLabel label = new JLabel(currentEntry.getName());
		label.setIcon(currentEntry.getIcon());
		label.setFont(label.getFont().deriveFont(Font.BOLD, 14));
		label.setLocation(10, 7);
		label.setSize(300, 30);
		pane.add(label);
		
		JLabel login = new JLabel(currentEntry.getAccount());
		login.setFont(label.getFont().deriveFont(Font.BOLD, 13));
		login.setForeground(Color.gray);
		login.setLocation(30, 15);
		login.setSize(300, 50);
		pane.add(login);
		
		
		JLabel fonction = new JLabel(currentEntry.getTitle());
		fonction.setForeground(Color.black);
		fonction.setLocation(30, 40);
		fonction.setSize(300, 30);
		add(fonction);
		
		
		final JButton mail = new JButton(currentEntry.getMail());
		mail.setBorder(BorderFactory.createEmptyBorder());
		mail.setBorderPainted(false);
		mail.setFocusPainted(false);
		mail.setHorizontalAlignment(SwingConstants.LEFT);
		mail.setOpaque(false);
		mail.setForeground(Color.gray);
		mail.setLocation(30, 55);
		mail.setSize(300, 30);
		mail.addMouseListener(new MouseAdapter() {
			
			public void mouseExited(MouseEvent e) {
				mail.setForeground(Color.gray);
			}
			
			public void mouseEntered(MouseEvent e) {
				mail.setForeground(Color.blue);
			}
			
			public void mouseClicked(MouseEvent e) {
				try {
					Desktop desktop = Desktop.getDesktop();
					URI uri = new URI("mailto:" + mail.getText());
					desktop.mail(uri);
				} catch (URISyntaxException e1) {
					e1.printStackTrace();
				} catch (IOException ioe) {
					ioe.printStackTrace();
				}
			}
		});
		add(mail);
		
		
		JLabel tel = new JLabel("tel. : " + currentEntry.getTelephone());
		tel.setForeground(Color.gray);
		tel.setLocation(30, 70);
		tel.setSize(300, 30);
		add(tel);
		
		JLabel adress = new JLabel("addr. : " + currentEntry.getStreetAdress());
		adress.setForeground(Color.gray);
		adress.setLocation(30, 85);
		adress.setSize(300, 30);
		add(adress);
		
		JLabel adress2 = new JLabel(currentEntry.getZipCode() + " " + currentEntry.getCity() );
		adress2.setForeground(Color.gray);
		adress2.setLocation(68, 100);
		adress2.setSize(300, 30);
		add(adress2);
		
		JLabel office = new JLabel("Bureau : " + currentEntry.getOffice());
		office.setForeground(Color.gray);
		office.setLocation(30, 115);
		office.setSize(300, 30);
		add(office);
		
		
		
		if(!isVisible())
			setVisible(true);
	}
	
	
}
