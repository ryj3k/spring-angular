package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.entity.Word;

public interface WordRepository extends JpaRepository<Word, Long>{
	
	@Query("SELECT distinct w FROM Word w JOIN FETCH w.translation")
	public List<Word> getAllFull();
	
	@Query("SELECT distinct w FROM Word w JOIN FETCH w.translation where w.id = :id")
	public Word getFull(@Param(value = "id") Long id);
}