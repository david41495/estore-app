package com.simplilearn.estore.admin.model;

public class User {

	private Long userId;

	public Long getUserId() {
	    return userId;
	}

	public void setUserId(Long userId) {
	    this.userId = userId;
	}


	String street;
	String city;
	String state;
	String country;
	Integer pincode;
	
	public User() {
		
	}

	
	public User(String street, String city, String state, String country, Integer pincode) {
		super();
		this.street = street;
		this.city = city;
		this.state = state;
		this.country = country;
		this.pincode = pincode;
	}


	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Integer getPincode() {
		return pincode;
	}

	public void setPincode(Integer pincode) {
		this.pincode = pincode;
	}

	@Override
	public String toString() {
		return String.format("User [street=%s, city=%s, state=%s, country=%s, pincode=%s]", street, city, state,
				country, pincode);
	}



}


