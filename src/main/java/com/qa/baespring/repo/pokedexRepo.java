package com.qa.baespring.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.qa.baespring.domain.pokedex;


@Repository
public interface pokedexRepo extends JpaRepository <pokedex, Long > {
	
	@Query(value = "SELECT * FROM pokedex WHERE name = ?1", nativeQuery=true)
	Optional<pokedex> findByName(String name);

}
