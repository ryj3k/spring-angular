package com.example.dto;

import java.util.Set;

import com.example.entity.Translation;

public class WordDTO {
	private Long id;
	private String name;
	private Set<Translation> translations;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Set<Translation> getTranslations() {
		return translations;
		
	}
	public void setTranslations(Set<Translation> translations) {
		this.translations = translations;
	}
}
