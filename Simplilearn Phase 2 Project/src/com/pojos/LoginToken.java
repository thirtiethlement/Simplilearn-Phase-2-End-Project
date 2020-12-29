package com.pojos;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class LoginToken 
{
	@Id
	@GeneratedValue
	private int ID;
	private String username;
	private String password;
	
	public LoginToken() {}
	public LoginToken(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
