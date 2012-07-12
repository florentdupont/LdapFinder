package flo.actions;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JList;

import flo.DirectoryEntry;
import flo.DirectoryEntryView;

public class DirectoryListMouseAction implements MouseListener {
	
	private JList entries;
	
	public DirectoryListMouseAction(JList entries) {
		this.entries = entries;
	}
	

	public void mouseClicked(MouseEvent e) {
		if(e.getClickCount() == 2) {
			DirectoryEntry entry = (DirectoryEntry)entries.getModel().getElementAt(entries.getSelectedIndex());
			System.out.println(entry.getTitle());
			
			DirectoryEntryView view = DirectoryEntryView.getInstance();
			view.setCurrentEntry(entry);
		}
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}

	public void mousePressed(MouseEvent e) {
	}

	public void mouseReleased(MouseEvent e) {
	}

}
