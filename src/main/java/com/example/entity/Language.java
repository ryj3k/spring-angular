package com.example.entity;

import lombok.*;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "el_language")
public class Language extends BaseEntity implements Serializable{
	
	
	private static final long serialVersionUID = 1L;

	private String name;
	
	private String shortName;
	
	@OneToMany(mappedBy = "language")
	private Set<Word> words;

}
