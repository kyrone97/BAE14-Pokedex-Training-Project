package com.qa.baespring.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qa.baespring.domain.pokedex;


@Repository
public interface pokedexRepo extends JpaRepository <pokedex, Long > {

}
