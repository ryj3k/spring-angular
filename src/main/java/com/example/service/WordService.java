package com.example.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ParseException;
import org.springframework.stereotype.Service;

import com.example.dto.WordDTO;
import com.example.entity.Word;
import com.example.repository.WordRepository;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class WordService {

	@Resource
	private WordRepository wordRepository;
	

	@Autowired
    private ModelMapper modelMapper;
	
	
	public WordDTO saveOrUpdateWord(WordDTO dto){
		return convertToDto(wordRepository.save(convertToEntity(dto)));		
	}
	
	public List<WordDTO> getAll(){		
		return wordRepository.getAllFull().stream().map(word -> convertToDto(word)).collect(Collectors.toList());
	}

	public WordDTO findOne(Long id){
		return convertToDto(wordRepository.getOne(id));
	}
	
	public WordDTO convertToDto(Word word) {
		if(word == null){
			return null;
		}
		WordDTO dto = modelMapper.map(word, WordDTO.class);		
		dto.setTranslations(word.getTranslation());
		return dto;
	}
	
	public Word convertToEntity(WordDTO customerDTO) throws ParseException {
		Word word = modelMapper.map(customerDTO, Word.class);   
		word.setTranslation(customerDTO.getTranslations());
	    if (customerDTO.getId() != null) {
	    	Word oldWord = wordRepository.getFull(customerDTO.getId());
	       
	    }
	    return word;
	}
	
}
