package com.qa.baespring.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.qa.baespring.domain.pokedex;
import com.qa.baespring.exception.PokemonNotFoundException;
import com.qa.baespring.repo.pokedexRepo;

@Service
public class pokedexService {
	
	private pokedexRepo repo;
	
	public pokedexService(pokedexRepo repo) {
		this.repo = repo;
		}
	
	// get by ID 
		public pokedex getById(long id) {
			//return repo.findById(id).get(); //.get will either get the user if exists or throw the exception no such element)
			return repo.findById(id).orElseThrow(PokemonNotFoundException::new);
		}
	
	//get ALL pokemon
		public List<pokedex>getAll(){
			return repo.findAll();
		}
	
	//get by name
		public pokedex getByName(String name) {
			return repo.findByName(name).get();
		}
		
	//get by type
		public List<pokedex> getByType(String type) {
			return repo.findByType(type);
		}
		
	//get by moves
		public List<pokedex> getByMoves(String moves) {
			return repo.findByMoves(moves);
		}
	
	//get by type
		public List<pokedex> getByWeakness(String weakness) {
			return repo.findByWeakness(weakness);
		}
		
	//creating a new pokemon entry
		public pokedex create(pokedex pokemon) {
			return repo.saveAndFlush(pokemon);
		}
		
	//update a pokemon by id
		public pokedex update(long id, pokedex pokemon) {
			pokedex existing = repo.findById(id).get(); //orElseThrow(UserNotUpdatedException::new); // get the existing user
			existing.setName(pokemon.getName()); // change the pokemon name to new pokemon name
			existing.setType(pokemon.getType()); // change existing pokemon type to new pokemon type
			existing.setWeakness(pokemon.getWeakness()); // change existing pokemon weakness to new pokemon weakness
			existing.setMoves(pokemon.getMoves()); //
			return repo.saveAndFlush(existing);
		}
		
	//update a pokemon
		public pokedex update(String name, pokedex pokemon) {
			pokedex existing = repo.findByName(name).get(); //orElseThrow(UserNotUpdatedException::new); // get the existing user
			existing.setName(pokemon.getName()); // change the pokemon name to new pokemon name
			existing.setType(pokemon.getType()); // change existing pokemon type to new pokemon type
			existing.setWeakness(pokemon.getWeakness()); // change existing pokemon weakness to new pokemon weakness
			existing.setMoves(pokemon.getMoves()); //
			return repo.saveAndFlush(existing);
		}
		
	//Delete a pokemon by ID
		public boolean delete(long id) {
			repo.deleteById(id);
			return !repo.existsById(id);
		}

		
}
