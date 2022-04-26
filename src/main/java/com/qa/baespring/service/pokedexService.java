package com.qa.baespring.service;

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
	
	
	
	

		
		
		
		
}
