package com.qa.baespring.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
	
	@Test
	public void getByName() {
		Optional<pokedex> OptionalOutput = Optional.of(new pokedex(1L, "Charizard", "Incinerate", "Fire","Electric"));
		pokedex output = new pokedex(1L, "Charizard", "Incinerate", "Fire","Electric");

		Mockito.when(this.repo.findByName("Charizard")).thenReturn(OptionalOutput);

		assertEquals(output, this.service.getByName("Charizard"));

		Mockito.verify(this.repo, Mockito.times(1)).findByName("Charizard");
	}
	
	@Test
	public void getByType() {
		List<pokedex> output = new ArrayList<>();
		output.add(new pokedex(1L,"Charizard", "Incinerate", "Fire","Electric"));
		List<pokedex> output1 = new ArrayList<>();
		output1.add(new pokedex(1L,"Charizard", "Incinerate", "Fire","Electric"));

		Mockito.when(this.repo.findByType("Fire")).thenReturn(output);

		assertEquals(output1, this.service.getByType("Fire"));

		Mockito.verify(this.repo, Mockito.times(1)).findByType("Fire");
	}
	
	@Test
	public void getByMoves() {
		List<pokedex> output = new ArrayList<>();
		output.add(new pokedex(1L,"Charizard", "Incinerate", "Fire","Electric"));
		List<pokedex> output1 = new ArrayList<>();
		output1.add(new pokedex(1L,"Charizard", "Incinerate", "Fire","Electric"));

		Mockito.when(this.repo.findByMoves("Incinerate")).thenReturn(output);

		assertEquals(output1, this.service.getByMoves("Incinerate"));

		Mockito.verify(this.repo, Mockito.times(1)).findByMoves("Incinerate");
	}
	
	@Test
	public void getByWeakness() {
		List<pokedex> output = new ArrayList<>();
		output.add(new pokedex(1L,"Charizard", "Incinerate", "Fire","Electric"));
		List<pokedex> output1 = new ArrayList<>();
		output1.add(new pokedex(1L,"Charizard", "Incinerate", "Fire","Electric"));

		Mockito.when(this.repo.findByWeakness("Electric")).thenReturn(output);

		assertEquals(output1, this.service.getByWeakness("Electric"));

		Mockito.verify(this.repo, Mockito.times(1)).findByWeakness("Electric");
	}
	
	@Test
	public void createTest() {
		pokedex input = new pokedex("Charizard", "Incinerate", "Fire","Electric");
		pokedex output = new pokedex(1L,"Charizard", "Incinerate", "Fire","Electric");

		Mockito.when(this.repo.saveAndFlush(input)).thenReturn(output);

		assertEquals(output, this.service.create(input));

		Mockito.verify(this.repo, Mockito.times(1)).saveAndFlush(input);
	}
	
	
	@Test
	public void updateTest() {
		pokedex input = new pokedex("Charizard", "Incinerate", "Fire","Electric");
		Optional<pokedex> existing = Optional.of(new pokedex(1L, "Charizard", "Incinerate", "Fire","Electric"));
		pokedex output = new pokedex(1L, "Charizard", "Incinerate", "Fire","Electric");

		Mockito.when(this.repo.findById(1L)).thenReturn(existing);
		Mockito.when(this.repo.saveAndFlush(output)).thenReturn(output);

		assertEquals(output, this.service.update(1L, input));

		Mockito.verify(this.repo, Mockito.times(1)).findById(1L);
		Mockito.verify(this.repo, Mockito.times(1)).saveAndFlush(output);
	}
	
	@Test
	public void updateTestByName() {
		pokedex input = new pokedex("Charizard", "Incinerate", "Fire","Electric");
		Optional<pokedex> existing = Optional.of(new pokedex(1L, "Charizard", "Incinerate", "Fire","Electric"));
		pokedex output = new pokedex(1L, "Charizard", "Incinerate", "Fire","Electric");

		Mockito.when(this.repo.findByName("Charizard")).thenReturn(existing);
		Mockito.when(this.repo.saveAndFlush(output)).thenReturn(output);

		assertEquals(output, this.service.update("Charizard", input));

		Mockito.verify(this.repo, Mockito.times(1)).findByName("Charizard");
		Mockito.verify(this.repo, Mockito.times(1)).saveAndFlush(output);
	
	}
	@Test
	public void deleteTest() {
		Mockito.when(this.repo.existsById(1L)).thenReturn(false);

		assertTrue(this.service.delete(1L));

		Mockito.verify(this.repo, Mockito.times(1)).deleteById(1L);
		Mockito.verify(this.repo, Mockito.times(1)).existsById(1L);
	}
	
	
	
	
	
	
	
	
}
