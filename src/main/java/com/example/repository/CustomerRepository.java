package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entity.Customer;


public interface CustomerRepository extends JpaRepository<Customer, Long>{
	
	Customer findByName(String firstName);

	List<Customer> findBySurname(String lastName);

}
