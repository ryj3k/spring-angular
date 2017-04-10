package com.example.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.CustomerDTO;
import com.example.service.CustomerService;



@RestController
public class CustomerRest {
	
	@Autowired
	private CustomerService customerService;	
	
	@RequestMapping("/customer/all")
	public List<CustomerDTO> getAll(){
		return customerService.findAll();
	}

}
