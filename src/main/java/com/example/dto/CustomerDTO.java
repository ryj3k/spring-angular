package com.example.dto;

import lombok.*;

import java.util.Arrays;
import java.util.List;

@Data
public class CustomerDTO {
	
	private Long id;
	private String name;
	private String surname;
	private String password;
	private List<String> roles;


}
