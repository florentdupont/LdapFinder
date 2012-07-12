package flo.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import flo.ConnexionLDAP;
import flo.DirectoryEntry;
import flo.LdapFinder;

public class SearchAction implements ActionListener {
	
	private LdapFinder directory;
	
	public SearchAction(LdapFinder directory) {
		this.directory = directory;
	}
	
	public void actionPerformed(ActionEvent e) {
	System.out.println("action");
			
		// je récupère la requête : 
		String searchField = directory.getSearchField();
		String searchValue = directory.getSearchValue();

		System.out.println("recherche pour " + searchField + " contient " + searchValue);
		// effectue la requete
		// 
		try {
			ConnexionLDAP connexion = new ConnexionLDAP();
			connexion.init();
			List<DirectoryEntry> entries = connexion.search(searchField, searchValue);
			System.out.println("fin recherche");
			directory.setEntries(entries);
			
		} catch (IOException e1) {
			JOptionPane.showMessageDialog(directory, "le fichier de config n'est pas correct !\n" + e1.getMessage());
		}
		
		
		// passe les résultat au directory pour qu'il les affiche
		//directory.setEntries(createFakeList());
		
		
	}
	
	
	List<DirectoryEntry> createFakeList() {
		
		  List<DirectoryEntry> result = new ArrayList<DirectoryEntry>();
		  
		  result.add(new DirectoryEntry("Florent DUPONT (Ext Thales)", "pfdt12811", "A216", "ext.thales.florent.dupont@sncf.fr", "37 93 39"));
		  result.add(new DirectoryEntry("Julien BIDET (Ext Thales)", "pfdt12811", "A216", "ext.thales.florent.dupont@sncf.fr", "37 93 39"));
		  result.add(new DirectoryEntry("Cyril HUSAR (Ext Thales)", "pfdt12811", "A216", "ext.thales.florent.dupont@sncf.fr", "37 93 39"));
		  result.add(new DirectoryEntry("Emmanuel LEGROS (Ext Thales)", "pfdt12811", "A216", "ext.thales.florent.dupont@sncf.fr", "37 93 39"));
		  result.add(new DirectoryEntry("Florent DUPONT (Ext Thales)", "pfdt12811", "A216", "ext.thales.florent.dupont@sncf.fr", "37 93 39"));
		  result.add(new DirectoryEntry("Julien BIDET (Ext Thales)", "pfdt12811", "A216", "ext.thales.florent.dupont@sncf.fr", "37 93 39"));
		  result.add(new DirectoryEntry("Cyril HUSAR (Ext Thales)", "pfdt12811", "A216", "ext.thales.florent.dupont@sncf.fr", "37 93 39"));
		  result.add(new DirectoryEntry("Emmanuel LEGROS (Ext Thales)", "pfdt12811", "A216", "ext.thales.florent.dupont@sncf.fr", "37 93 39"));
		  result.add(new DirectoryEntry("Florent DUPONT (Ext Thales)", "pfdt12811", "A216", "ext.thales.florent.dupont@sncf.fr", "37 93 39"));
		  result.add(new DirectoryEntry("Julien BIDET (Ext Thales)", "pfdt12811", "A216", "ext.thales.florent.dupont@sncf.fr", "37 93 39"));
		  result.add(new DirectoryEntry("Cyril HUSAR (Ext Thales)", "pfdt12811", "A216", "ext.thales.florent.dupont@sncf.fr", "37 93 39"));
		  result.add(new DirectoryEntry("Emmanuel LEGROS (Ext Thales)", "pfdt12811", "A216", "ext.thales.florent.dupont@sncf.fr", "37 93 39"));
		 
		  return result;
	  }

}
