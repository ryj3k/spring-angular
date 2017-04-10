package com.example.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ParseException;
import org.springframework.stereotype.Service;

import com.example.dto.CustomerDTO;
import com.example.entity.Customer;
import com.example.repository.CustomerRepository;



@Service
public class CustomerService {
	
	@Resource
	private CustomerRepository customerRepository;
	
	
	@Autowired
    private ModelMapper modelMapper;
	
	public CustomerDTO findByName(String name){
		return convertToDto(customerRepository.findByName(name));
	}

	public List<CustomerDTO> findBySurname(String lastName){
		List<Customer> list = customerRepository.findBySurname(lastName);
		return list.stream().map(customer -> convertToDto(customer)).collect(Collectors.toList());
	}
	
	public List<CustomerDTO> findAll(){
		List<Customer> list = customerRepository.findAll();
		return list.stream().map(customer -> convertToDto(customer)).collect(Collectors.toList());
	}
	
	private CustomerDTO convertToDto(Customer customer) {
		CustomerDTO customerDTO = modelMapper.map(customer, CustomerDTO.class);	    
	    return customerDTO;
	}
	
	private Customer convertToEntity(CustomerDTO customerDTO) throws ParseException {
		Customer customer = modelMapper.map(customerDTO, Customer.class);   
	  
	    if (customerDTO.getId() != null) {
	    	Customer oldPost = customerRepository.findOne(customerDTO.getId());
	       
	    }
	    return customer;
	}

}
