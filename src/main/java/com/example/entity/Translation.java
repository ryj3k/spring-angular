package com.example.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "el_translation")
public class Translation extends BaseEntity implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String value;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
