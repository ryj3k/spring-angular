package com.example.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "el_language")
public class Language extends BaseEntity implements Serializable{
	
	
	private static final long serialVersionUID = 1L;

	private String name;
	
	private String shortName;
	
	@OneToMany(mappedBy = "language")
	private Set<Word> words;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public Set<Word> getWords() {
		return words;
	}

	public void setWords(Set<Word> words) {
		this.words = words;
	}

}
