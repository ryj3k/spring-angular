package com.example.entity;

import lombok.*;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;



@Data
@Entity
@Table(name="el_dupa")
public class User extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	private String name;
	private String surname;
	private String email;
	private String password;


}
