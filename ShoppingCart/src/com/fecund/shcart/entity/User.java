package com.fecund.shcart.entity;

import java.util.ArrayList;
import java.util.List;

public class User {
	private int userid;
	private String firstname;
	private String lastname;
	private String email;
	private String contactno;
	private String username;
	private String password;
	private String confirmpassword;
	private String status;
	private int usertype;
	private List<String> statuslist;

	public User() {
		super();
	}

	public List<String> getStatuslist() {
		return statuslist;
	}

	public void setStatuslist(List<String> statuslist1) {
	
		statuslist.add("Waiting");
		statuslist.add("Approved");
		statuslist.add("Reject");
		this.statuslist = statuslist;
	}

	public int getUserid() {
		return userid;
	}

	public int getUsertype() {
		return usertype;
	}

	public void setUsertype(int usertype) {
		this.usertype = usertype;
	}

	public void setUserid(int userid) {
		this.userid = userid;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getContactno() {
		return contactno;
	}

	public void setContactno(String contactno) {
		this.contactno = contactno;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmpassword() {
		return confirmpassword;
	}

	public void setConfirmpassword(String confirmpassword) {
		this.confirmpassword = confirmpassword;
	}

	public void setStatuslist(String string) {
		statuslist.add(string);
		
	}

}