package com.qa.baespring.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.qa.baespring.domain.pokedex;
import com.qa.baespring.repo.pokedexRepo;

@SpringBootTest
public class pokedexServiceUnitTest {
	
	
	@Autowired
	private pokedexService service;

	@MockBean
	private pokedexRepo repo;
	
	@Test
	public void getAllTest() {
		List<pokedex> output = new ArrayList<>();
		output.add(new pokedex("Charizard", "Incinerate", "Fire","Electric"));

		Mockito.when(this.repo.findAll()).thenReturn(output);

		assertEquals(output, this.service.getAll());

		Mockito.verify(this.repo, Mockito.times(1)).findAll();
	}

	@Test
	public void getByIdTest() {
		Optional<pokedex> OptionalOutput = Optional.of(new pokedex(1L, "Charizard", "Incinerate", "Fire","Electric"));
		pokedex output = new pokedex(1L, "Charizard", "Incinerate", "Fire","Electric");

		Mockito.when(this.repo.findById(1L)).thenReturn(OptionalOutput);

		assertEquals(output, this.service.getById(1L));

		Mockito.verify(this.repo, Mockito.times(1)).findById(1L);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
