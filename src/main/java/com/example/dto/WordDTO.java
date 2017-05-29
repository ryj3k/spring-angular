package com.example.dto;

import java.util.Set;
import java.util.stream.Collectors;

import com.example.entity.Translation;

public class WordDTO {
	private Long id;
	private String name;
	private Set<String> translations;
	
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
		return translations.stream().map(t ->{
			Translation tr = new Translation();
			tr.setValue(t);
			return tr;
		}).collect(Collectors.toSet());
		
	}
	public void setTranslations(Set<String> translations) {
		this.translations = translations;
	}
}
