package flo;

import java.net.URL;

import javax.swing.Icon;
import javax.swing.ImageIcon;

public class DirectoryEntry {
	private String account;
	private String name;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	private String office;
	private String telephone;
	
	private String streetAdress;
	private String zipCode;
	private String city;
	private String mail;
	private Icon icon;
	private String title;

 
	public DirectoryEntry(String name, String account, String office, String mail, String telephone) {
		this.name = name;
		this.account = account;
		this.office = office;
		this.mail = mail;
		this.telephone = telephone;
	}
	
	public DirectoryEntry() {
		
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getOffice() {
		return office;
	}

	public void setOffice(String office) {
		this.office = office;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getStreetAdress() {
		return streetAdress;
	}

	public void setStreetAdress(String streetAdress) {
		this.streetAdress = streetAdress;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	
	
	public Icon getIcon() {
		if (icon == null) {
			URL url = this.getClass().getClassLoader().getResource("user.png");
			icon = new ImageIcon(url);
		}
		return icon;
	}

	// Override standard toString method to give a useful result
	public String toString() {
		return title;
	}
}