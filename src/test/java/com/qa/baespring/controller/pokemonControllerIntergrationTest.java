package com.qa.baespring.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.baespring.domain.pokedex;

@SpringBootTest
@AutoConfigureMockMvc
@Sql(scripts = {"classpath:testschema.sql", "classpath:testdata.sql"}, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
@ActiveProfiles("test")
public class pokemonControllerIntergrationTest {
	
	@Autowired
	private MockMvc mvc;
	
	
	@Autowired
	private ObjectMapper mapper;
	
	// Create test
	@Test // test are void because they don't return anything
	public void createTest() throws Exception {
		
	pokedex entry = new pokedex("Charizard", "Incinerate", "Fire","Electric");
	String entryAsJSON = mapper.writeValueAsString(entry);
	
	pokedex result = new pokedex(2L, "Charizard", "Incinerate", "Fire","Electric");
	String resultAsJSON = mapper.writeValueAsString(result);
	
	mvc.perform(post("/pokedex/create")
			.contentType(MediaType.APPLICATION_JSON)
			.content(entryAsJSON))
			.andExpect(status().isCreated())
			.andExpect(content().json(resultAsJSON));
	
	}
	
	// Get All test
	@Test
	public void getAllTest() throws Exception{
		pokedex pokemon = new pokedex(1L,"Charizard", "Incinerate", "Fire","Electric");
		List<pokedex> output = new ArrayList<>();
		output.add(pokemon);
		String outputAsJSON = mapper.writeValueAsString(output);
		
		mvc.perform(get("/pokedex/getAll")
		.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(content().json(outputAsJSON));
				
	}
	
	@Test
	public void getByName()throws Exception {
		pokedex pokemon = new pokedex(1L,"Charizard", "Incinerate", "Fire","Electric");
		String output1AsJSON = mapper.writeValueAsString(pokemon);
		
		mvc.perform(get("/pokedex/getByName/Charizard")
			.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(content().json(output1AsJSON));
	}
	
	@Test
	public void getByMoves()throws Exception {
		pokedex pokemon = new pokedex(1L,"Charizard", "Incinerate", "Fire","Electric");
		pokedex[] pokedexarray = {pokemon};
		String output1AsJSON = mapper.writeValueAsString(pokedexarray);
		
		
		mvc.perform(get("/pokedex/getByMoves/Incinerate")
			.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(content().json(output1AsJSON));
	}
	
	@Test
	public void getByType()throws Exception {
		pokedex pokemon = new pokedex(1L,"Charizard", "Incinerate", "Fire","Electric");
		pokedex[] pokedexarray = {pokemon};
		String output1AsJSON = mapper.writeValueAsString(pokedexarray);
		
		
		mvc.perform(get("/pokedex/getByType/Fire")
			.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(content().json(output1AsJSON));
	
	}
	
	@Test
	public void getByWeakness()throws Exception {
		pokedex pokemon = new pokedex(1L,"Charizard", "Incinerate", "Fire","Electric");
		pokedex[] pokedexarray = {pokemon};
		String output1AsJSON = mapper.writeValueAsString(pokedexarray);
		
		
		mvc.perform(get("/pokedex/getByWeakness/Electric")
			.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(content().json(output1AsJSON));
	
	}
	
	@Test
	public void getById()throws Exception {
		pokedex pokemon1 = new pokedex(1L,"Charizard", "Incinerate", "Fire","Electric");
		String output1AsJSON = mapper.writeValueAsString(pokemon1);
		
		mvc.perform(get("/pokedex/getById/1")
			.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(content().json(output1AsJSON));
	}
	
	
	// update by ID test
	@Test
	public void updateTest() throws Exception {
		pokedex pokemon2 = new pokedex("Charizard", "Incinerate", "Fire","Electric"); // Create a java object representing your update object
		String output2AsJSON = mapper.writeValueAsString(pokemon2); // Convert that to JSON

		pokedex result1 = new pokedex(1L,"Charizard", "Incinerate", "Fire","Electric"); // Create a java object representing what you expect to get back
		String resul1tAsJSON = mapper.writeValueAsString(result1); // Convert that to JSON

		mvc.perform(put("/pokedex/update/1")
				.contentType(MediaType.APPLICATION_JSON)
				.content(output2AsJSON))
				.andExpect(status().isCreated())
				.andExpect(content().json(resul1tAsJSON));// Send your request and check for the right response code and content
	}
	
	//	update by name test
	@Test
	public void updateTestByName() throws Exception {
		pokedex pokemon2 = new pokedex("Charizard", "Incinerate", "Fire","Electric"); // Create a java object representing your update object
		String output2AsJSON = mapper.writeValueAsString(pokemon2); // Convert that to JSON

		pokedex result1 = new pokedex(1L,"Charizard", "Incinerate", "Fire","Electric"); // Create a java object representing what you expect to get back
		String resul1tAsJSON = mapper.writeValueAsString(result1); // Convert that to JSON

		mvc.perform(put("/pokedex/update/name/Charizard")
				.contentType(MediaType.APPLICATION_JSON)
				.content(output2AsJSON))
				.andExpect(status().isCreated())
				.andExpect(content().json(resul1tAsJSON));// Send your request and check for the right response code and content
	}
	

	@Test
	public void deleteTest() throws Exception {
		// Send the request
		// Check the response code is as expected (likely no content)
		mvc.perform(delete("/pokedex/delete/1")
			.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isNoContent());
	}

}
