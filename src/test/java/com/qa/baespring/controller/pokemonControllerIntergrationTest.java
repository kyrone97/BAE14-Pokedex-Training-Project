package com.qa.baespring.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
	
	
	
	
	
	
	

}
