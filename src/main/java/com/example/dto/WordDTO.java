package com.example.dto;

import java.util.Set;

import com.example.entity.Translation;
import lombok.*;

@Data
@Builder
public class WordDTO {
	private Long id;
	private  String name;
	private Set<Translation> translations;
}
