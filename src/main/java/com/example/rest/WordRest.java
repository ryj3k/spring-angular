package com.example.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.WordDTO;
import com.example.service.WordService;

@RestController
public class WordRest {
	
	@Autowired
	private WordService wordService;
	
	@RequestMapping("api/word/save")
	public ResponseEntity<WordDTO> saveWord(@RequestBody WordDTO dto){
		WordDTO newDto = wordService.saveOrUpdateWord(dto);
		return ResponseEntity.ok(newDto);
	}
	
	@RequestMapping("api/word/all")
	public ResponseEntity<List<WordDTO>> getAll(){	
		return  ResponseEntity.ok(wordService.getAll());
	}

}
