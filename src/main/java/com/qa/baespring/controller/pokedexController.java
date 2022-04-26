package com.qa.baespring.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.qa.baespring.domain.pokedex;
import com.qa.baespring.service.pokedexService;

@RestController
@CrossOrigin //requests can come from anywhere
public class pokedexController {

	private pokedexService service;
	
	public pokedexController(pokedexService service) {
		super();
		this.service = service;
	}
	
	//Get Request
	@GetMapping("/getById/{id}") // localhost:8080/getById/
	public ResponseEntity<pokedex> getById (@PathVariable long id) {
		return new ResponseEntity<pokedex>(service.getById(id), HttpStatus.OK);
	}
	
	
	@GetMapping("/getAll") // localhost:8080/getAll
	public ResponseEntity<List<pokedex>>getAll() {
		return new ResponseEntity<List<pokedex>>(service.getAll(),HttpStatus.OK);
		
		
	}
	
	
	
}
