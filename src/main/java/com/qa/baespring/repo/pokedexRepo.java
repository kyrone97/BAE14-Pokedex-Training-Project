package com.qa.baespring.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.qa.baespring.domain.pokedex;


@Repository
public interface pokedexRepo extends JpaRepository <pokedex, Long > {
	
	//get by name query
	@Query(value = "SELECT * FROM pokedex WHERE name = ?1", nativeQuery=true)
	Optional<pokedex> findByName(String name);
	
	// get by type query
	// don't need an optional if using a list 
	@Query(value = "SELECT * FROM pokedex WHERE type = ?1", nativeQuery=true)
	List<pokedex> findByType(String type);

	@Query(value = "SELECT * FROM pokedex WHERE weakness = ?1", nativeQuery=true)
	List<pokedex> findByWeakness(String weakness);

	
}
