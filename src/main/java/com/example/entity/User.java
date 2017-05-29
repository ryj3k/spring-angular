package com.example.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;



@Entity
@Table(name="el_dupa")
public class User extends BaseEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	
	private String name;
	
	private String surname;
	
	private String email;
	
	private String password;	
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	
}
