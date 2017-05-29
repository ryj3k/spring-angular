package com.example.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;
@Entity
@Table(name = "el_group")
public class Group extends BaseEntity implements Serializable{

	
	private static final long serialVersionUID = 1L;
	
	private String name;
	
	private Integer number;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

}
