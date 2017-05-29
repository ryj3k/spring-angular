package com.example.service;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ParseException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.dto.CustomerDTO;
import com.example.dto.WordDTO;
import com.example.entity.User;
import com.example.entity.Word;
import com.example.repository.WordRepository;

@Service
public class WordService {

	@Resource
	private WordRepository wordRepository;
	

	@Autowired
    private ModelMapper modelMapper;
	
	
	public WordDTO saveOrUpdateWord(WordDTO dto){
		return convertToDto(wordRepository.save(convertToEntity(dto)));
		
	}
	
	
	
	public WordDTO convertToDto(Word customer) {
		if(customer == null){
			return null;
		}
		WordDTO dto = modelMapper.map(customer, WordDTO.class);
		List<String>list = customer.getTranslation().stream().map( t -> t.getValue()).collect(Collectors.toList());
		dto.setTranslations(new HashSet<>(list));
		return dto;
	}
	
	public Word convertToEntity(WordDTO customerDTO) throws ParseException {
		Word word = modelMapper.map(customerDTO, Word.class);   
		word.setTranslation(customerDTO.getTranslations());
	    if (customerDTO.getId() != null) {
	    	Word oldWord = wordRepository.findOne(customerDTO.getId());
	       
	    }
	    return word;
	}	
	
}
