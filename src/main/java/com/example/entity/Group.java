package com.example.entity;

import lombok.*;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;
@Data
@Entity
@Table(name = "el_group")
public class Group extends BaseEntity implements Serializable{

	
	private static final long serialVersionUID = 1L;
	
	private String name;
	
	private Integer number;

}
