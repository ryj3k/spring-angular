package com.example.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ParseException;
import org.springframework.stereotype.Service;

import com.example.dto.CustomerDTO;
import com.example.entity.User;
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
		List<User> list = customerRepository.findBySurname(lastName);
		return list.stream().map(customer -> convertToDto(customer)).collect(Collectors.toList());
	}
	
	public List<CustomerDTO> findAll(){
		List<User> list = customerRepository.findAll();
		return list.stream().map(customer -> convertToDto(customer)).collect(Collectors.toList());
	}
	
	private CustomerDTO convertToDto(User customer) {
		if(customer == null){
			return null;
		}		    
	    return modelMapper.map(customer, CustomerDTO.class);
	}
	
	private User convertToEntity(CustomerDTO customerDTO) throws ParseException {
		User customer = modelMapper.map(customerDTO, User.class);   
	  
	    if (customerDTO.getId() != null) {
	    	User oldPost = customerRepository.findOne(customerDTO.getId());
	       
	    }
	    return customer;
	}	

}
