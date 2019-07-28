package com.example.dto;

import com.example.entity.Translation;
import lombok.*;

import java.util.Set;

@Data
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class WordDTO {
    private Long id;
    private String name;
    private Set<Translation> translations;
}
