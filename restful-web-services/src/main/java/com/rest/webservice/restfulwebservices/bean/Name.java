package com.rest.webservice.restfulwebservices.bean;

public class Name {
	private String firstname;
	private String lastname;
	public Name() {
	}

	public Name(String firstname, String lastname) {
		this.firstname = firstname;
		this.lastname = lastname;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	@Override
	public String toString() {
		return "Name [firstname=" + firstname + ", lastname=" + lastname + "]";
	}

}
