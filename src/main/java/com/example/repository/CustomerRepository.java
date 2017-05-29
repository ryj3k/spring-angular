package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entity.User;


public interface CustomerRepository extends JpaRepository<User, Long>{
	
	User findByName(String firstName);

	List<User> findBySurname(String lastName);

}
