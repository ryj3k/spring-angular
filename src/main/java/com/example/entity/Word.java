package com.example.entity;

import lombok.*;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "el_word")
public class Word extends BaseEntity{
	
	private String name;
	
	@ManyToOne
	@JoinColumn(name = "lang_id")
	private Language language;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "word_translation", 
	joinColumns = {@JoinColumn(name = "word_id")},
	inverseJoinColumns = {@JoinColumn(name = "translation_id")})
	private Set<Translation> translation;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "group_id")
	private Group group;

}
