package com.example.test.service;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import lombok.extern.slf4j.Slf4j;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Slf4j
public class WordServiceTest {

	@Autowired
	private WordService wordService;
	
	@Before
	public void init(){
		MockitoAnnotations.initMocks(WordServiceTest.class);
	}


	@Test
	public void covetrToEntityTest(){
		WordDTO dto =  WordDTO.builder().build();
		dto.setName("Imie");
		dto.setTranslations(Collections.singleton(new Translation()));
		dto = wordService.saveOrUpdateWord(dto);
		WordDTO entity = wordService.findOne(dto.getId());
		assertNotNull(dto);
		assertNotNull(dto.getId());
		assertTrue(dto.getName().equals("Imie"));
		assertNotNull(entity.getTranslations());

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
