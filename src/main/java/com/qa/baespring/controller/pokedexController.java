package com.qa.baespring.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qa.baespring.domain.pokedex;
import com.qa.baespring.service.pokedexService;

@RestController
@CrossOrigin //requests can come from anywhere
@RequestMapping("/pokedex")
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
	
	//Get by name(get one pokemon by name)
	@GetMapping("getByName/{name}") // localhost:8080/getByName/name
	public ResponseEntity<pokedex> getByName(@PathVariable String name) {
		return new ResponseEntity<pokedex>(service.getByName(name), HttpStatus.OK);
		}
	
	@GetMapping("getByType/{type}") // localhost:8080/getByType/type
	public ResponseEntity<List<pokedex>> getByType(@PathVariable String type) {
		return new ResponseEntity<List<pokedex>>(service.getByType(type), HttpStatus.OK);
	}

	@GetMapping("getByWeakness/{weakness}") // localhost:8080/getByWeakness/weakness
	public ResponseEntity<List<pokedex>> getByWeakness(@PathVariable String weakness) {
		return new ResponseEntity<List<pokedex>>(service.getByWeakness(weakness), HttpStatus.OK);
	}
	
	@GetMapping("getByMoves/{moves}") // localhost:8080/getByMoves/moves
	public ResponseEntity<List<pokedex>> getByMoves(@PathVariable String moves) {
		return new ResponseEntity<List<pokedex>>(service.getByMoves(moves), HttpStatus.OK);
	}
	
	// Post
	@PostMapping("/create") // localhost:8080/create
	public ResponseEntity<pokedex>create(@RequestBody pokedex pokemon) {
		return new ResponseEntity<pokedex>(service.create(pokemon), HttpStatus.CREATED);
	}
	
	// Put
	@PutMapping("/update/{id}") // localhost:8080/pokedex/update/id
	public ResponseEntity<pokedex>update(@PathVariable long id, @RequestBody pokedex pokemon) {
		return new ResponseEntity<pokedex>(service.update(id, pokemon),HttpStatus.CREATED);
			
	}
	
	// Put
		@PutMapping("/update/name/{name}") // localhost:8080/update/name
		public ResponseEntity<pokedex>update(@PathVariable String name, @RequestBody pokedex pokemon) {
			return new ResponseEntity<pokedex>(service.update(name, pokemon),HttpStatus.CREATED);
				
	}
	// Delete//using a turnary if
		@DeleteMapping("/delete/{id}") //localhost:8080/delete/id
		public ResponseEntity<Boolean> delete(@PathVariable long id) {
			return (service.delete(id))? new ResponseEntity<Boolean>(HttpStatus.NO_CONTENT) : 
				new ResponseEntity<Boolean>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}
