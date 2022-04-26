package com.qa.baespring.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.qa.baespring.domain.pokedex;
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
			return repo.findById(id).get();
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

		
	//get by type
		public List<pokedex> getByWeakness(String weakness) {
			return repo.findByWeakness(weakness);
		}
		
	//creating a new pokemon entry
		public pokedex create(pokedex pokemon) {
			return repo.saveAndFlush(pokemon);
		}
		
}
