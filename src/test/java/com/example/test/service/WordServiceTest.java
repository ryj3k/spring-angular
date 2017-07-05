package com.example.test.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;

import com.example.dto.WordDTO;
import com.example.entity.Translation;
import com.example.entity.Word;
import com.example.repository.CustomerRepository;
import com.example.service.WordService;

@RunWith(MockitoJUnitRunner.class)
public class WordServiceTest {
	@InjectMocks
	WordService wordService = new WordService();
	
	@Spy
    private ModelMapper modelMapper = new ModelMapper();
	
	@Mock
	private CustomerRepository customerRepository;
	
	//@Before
	public void init(){
		MockitoAnnotations.initMocks(WordServiceTest.class);
	}
	
	@Test
	public void covetrToEntityTest(){
		WordDTO dto = new WordDTO();
		dto.setName("Imie");		
		
		Word entity = wordService.convertToEntity(dto);
		assertNotNull(entity);
		assertTrue(entity.getName().equals("Imie"));
		assertNotNull(entity.getTranslation());
		entity.getTranslation().forEach(n -> {
			System.out.println("Tlumacznie:" + n.getValue());
		});
		
	}
	
	@Test
	public void covetrToDTOTest(){
		Word word = new Word();
		word.setName("Imie");
		Translation tr1 = new Translation();
		tr1.setValue("22");
		
		Translation tr2 = new Translation();
		tr2.setValue("33");
		Set<Translation> set = new HashSet<Translation>(Arrays.asList(tr1, tr2));
		word.setTranslation(set);
		
		WordDTO dto = wordService.convertToDto(word);
		assertNotNull(dto);
		assertNotNull(dto.getTranslations());
		dto.getTranslations().forEach(t -> {
			System.out.println("Tlumacznie:" + t.getValue());

		});
		
	}
	
}
