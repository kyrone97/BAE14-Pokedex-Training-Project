package com.qa.baespring.exception;

import javax.persistence.EntityNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code =HttpStatus.NOT_FOUND, reason = "Pokemon was not successfully updated")
public class PokemonNotUpdatedException extends EntityNotFoundException{
	
}
