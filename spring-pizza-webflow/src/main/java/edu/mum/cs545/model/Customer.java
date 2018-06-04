package edu.mum.cs545.model;

import java.io.Serializable;

public class Customer implements Serializable {
 
	private static final long serialVersionUID = 1L;
	private String firstName;
    private String lastName;
    private String emailAddress;

    public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
    
    
}
