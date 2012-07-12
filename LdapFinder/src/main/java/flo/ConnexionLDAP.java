package flo;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.SizeLimitExceededException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;

public class ConnexionLDAP {

	
	private Hashtable<String, String> env;
	private DirContext ctx;
	
	public ConnexionLDAP() {}
	
	public void init() throws IOException {
		Properties properties = new Properties();
		
		String separator = System.getProperty("file.separator");
		String str = getApplicationPreferencesDir() + separator + "settings.properties";

		properties.load(new FileInputStream(str));

		final String host = properties.getProperty("host");
		final String port = properties.getProperty("port");
		final String username = properties.getProperty("username");
		final String password = properties.getProperty("password");
		final String baseDN = properties.getProperty("baseDN");
		
		if(host == null || "".equals(host))
			throw new IOException("host non renseigné");
		if(port == null || "".equals(port))
			throw new IOException("port non renseigné");
		if(username == null || "".equals(username))
			throw new IOException("username non renseigné");
		if(password == null || "".equals(password))
			throw new IOException("password non renseigné");
		if(baseDN == null || "".equals(baseDN))
			throw new IOException("baseDN non renseigné");
		
		env = new Hashtable<String, String>();
		String providerUrl = "ldap://" + host + ":" + port + "/" + baseDN;			

		System.out.println(providerUrl);
		
		env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");			
		env.put(Context.PROVIDER_URL, providerUrl);
		env.put(Context.SECURITY_AUTHENTICATION, "simple");
		env.put(Context.SECURITY_PRINCIPAL, username);
		env.put(Context.SECURITY_CREDENTIALS, password);

		
	}
	
	
	
	public List<DirectoryEntry> search(String field, String value) {
		
		List<DirectoryEntry> result = new ArrayList<DirectoryEntry>();
		try {
			ctx = new InitialDirContext(env);
			
			NamingEnumeration<SearchResult> results = null;
			if("Nom".equals(field)) {
				results = _search("name", value);
			}
			else if("Identifiant".equals(field)) {
				results = _search("sAMAccountName", value);
			}
			else if("Numéro de téléphone".equals(field)) {
				
				results = _search("telephoneNumber", value);
			}
			else if("Bureau".equals(field)) {
				results = _search("physicalDeliveryOfficeName", value);
			}
			
			
			while (results.hasMore()) {
				 
	            SearchResult searchResult = (SearchResult) results.next();
	            Attributes attributes = searchResult.getAttributes();
	            
	            DirectoryEntry entry = new DirectoryEntry();
	            
	            Attribute attr = attributes.get("cn");
	            String cn = (String) attr.get();
	            entry.setName(cn);
	            
	            attr = attributes.get("title");
	            String title = (String) (attr == null ? "" : attr.get());
	            entry.setTitle(title);
	            
	            attr = attributes.get("telephoneNumber");
	            String tel = (String) (attr == null ? "" : attr.get());
	            entry.setTelephone(tel);
	            
	            attr = attributes.get("sAMAccountName");
	            String account = (String) (attr == null ? "" : attr.get());
	            entry.setAccount(account);
	            
	            attr = attributes.get("physicalDeliveryOfficeName");
	            String office = (String) (attr == null ? "" : attr.get());
	            entry.setOffice(office);
	            
	            attr = attributes.get("streetAddress");
	            String street = (String) (attr == null ? "" : attr.get());
	            entry.setStreetAdress(street);
	            
	            attr = attributes.get("postalCode");
	            String postalCode = (String) (attr == null ? "" : attr.get());
	            entry.setZipCode(postalCode);
	            
	            attr = attributes.get("l");
	            String l = (String) (attr == null ? "" : attr.get());
	            entry.setCity(l);
	            
	            attr = attributes.get("mail");
	            String mail = (String) (attr == null ? "" : attr.get());
	            entry.setMail(mail);
	            
	            result.add(entry);
	            
	            
			}
			results.close();
			ctx.close();
			
			System.out.println("" + result.size() + " results found !" );
			
		} catch (NamingException e) {
			// e.printStackTrace();
			System.out.println(e.getMessage() + "\n" + e.getExplanation());
		}
		
		
		return result;
		
	}
	
	private NamingEnumeration<SearchResult> _search(String field, String value) {
		NamingEnumeration<SearchResult> results = null;
		try {
			SearchControls controls = new SearchControls();
			controls.setSearchScope(SearchControls.SUBTREE_SCOPE);
			controls.setCountLimit(30);
			
			String filter = field + "=" + value;
			
			System.out.println(filter);
			
			
			results = ctx.search("", filter, controls);
		} catch (NamingException e) {
			e.printStackTrace();
		}
		return results;
	}
	
	private String getApplicationPreferencesDir() {
		
		String separator = System.getProperty("file.separator");
		String path = System.getProperty("user.home") + separator + ".sncfdirectory";
		
		// s'il n'existe pas on le créé.
		File f = new File(path);
		if(!f.exists()) {
			f.mkdir();
		}
		
		return path;
	}
	
}
