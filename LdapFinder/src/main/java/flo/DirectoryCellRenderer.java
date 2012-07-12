package flo;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;

class DirectoryCellRenderer extends JPanel implements ListCellRenderer {
  
  private JLabel labelIcon;
  private JLabel labelName;
  private JLabel labelInfo;
  
  public DirectoryCellRenderer() {
	  
	  setBackground(Color.WHITE);
	  labelIcon = new JLabel();
	  labelName = new JLabel();
	  labelInfo = new JLabel();

	  setLayout(new GridBagLayout());

	  labelIcon.setOpaque(false);
	  labelIcon.setBackground(Color.WHITE);
	  //labelIcon.setBorder(BorderFactory.createLineBorder(Color.red));
	  
	  labelName.setOpaque(false);
	  Font defFont = labelName.getFont();
	  labelName.setFont(defFont.deriveFont(Font.BOLD, 13));
	  //labelName.setBorder(BorderFactory.createLineBorder(Color.GREEN));
	  labelInfo.setOpaque(false);
	  //labelInfo.setFont(font);
	  labelInfo.setForeground(Color.darkGray);
	  Font labelInfoFont = labelInfo.getFont();
	  labelInfo.setFont(labelInfoFont.deriveFont(Font.PLAIN));
	  
	  GridBagConstraints c = new GridBagConstraints();
	  c.gridx = 0;
	  c.gridy = 0;
	  c.fill = GridBagConstraints.NONE;
	  c.gridheight = 2;
	  c.insets = new Insets(10,10,10,10);
	  add(labelIcon, c);
	  
	  GridBagConstraints c2 = new GridBagConstraints();
	  c2.gridx = 1;
	  c2.gridy = 0;
	  c2.weightx = 1.0;
	  // décalage de 3 à partir du haut
	  c2.insets = new Insets(3, 0, 0, 0);
	  c2.fill = GridBagConstraints.HORIZONTAL;
	  add(labelName, c2);
	  
	  
	  GridBagConstraints c3 = new GridBagConstraints();
	  c3.gridx = 1;
	  c3.gridy = 1;
	  c3.weightx = 1.0;
	  // décalage de 10 vers la gauche
	  c3.insets = new Insets(0, 10, 0, 0);
	  c3.fill = GridBagConstraints.HORIZONTAL;
	  add(labelInfo, c3);
	  
	 // setBorder(BorderFactory.createLineBorder(Color.red));
  }

  public Component getListCellRendererComponent(JList list, Object value,
      int index, boolean isSelected, boolean cellHasFocus) {
	  
	  boolean isAlternative = false;
	if(index % 2 == 1) {
		isAlternative = true;
	}  
    DirectoryEntry entry = (DirectoryEntry) value;
    labelName.setText(entry.getName());
    
    
    StringBuilder builder = new StringBuilder();
    builder.append("<html>");
    builder.append(entry.getMail());
    builder.append("<font color='#707070'><i>- tel: ");
    builder.append(entry.getTelephone());
    builder.append(" - Bureau: ");
    builder.append(entry.getOffice());
    builder.append("</i></font></html>");
    
    
    labelInfo.setText(builder.toString());
    
    labelIcon.setIcon(entry.getIcon());
    if (isSelected) {
      setBackground(new Color(0x93AEFF));
    } else {
    	if(isAlternative)
    		setBackground(Color.white);
    	else {
    		setBackground(new Color(0xe0e0e0));
    	}
    }
   
    	
    return this;
}
}