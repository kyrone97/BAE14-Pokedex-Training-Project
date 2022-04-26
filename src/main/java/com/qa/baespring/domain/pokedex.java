package com.qa.baespring.domain;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


//used for modelling data into tables(maps from java/spring into database tables)
//will create the table in the database if it does not already exist
@Entity 
public class pokedex {
	
//	This makes the column a primary key
	@Id
//	This makes the column auto increment
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	// Creates a column called "name"
	@Column(unique = true, name = "name", nullable = false)
	private String name;
	
	// Creates a column called "moves"
	@Column(name = "moves", nullable = false)
	private String moves;
	
	//Creates a column called "type"
	@Column(name = "type", nullable = false)
	private String type;
	
	// Creates a column called "weakness"
	// This column CAN NOT be null
	@Column(nullable = false, name = "weakness")
	private String weakness;

	
	// Used for reading/selecting (and testing)
	public pokedex(long id, String name, String moves, String type, String weakness) {
		super();
		this.id = id;
		this.name = name;
		this.moves = moves;
		this.type = type;
		this.weakness = weakness;
	}
	
	// Used for creating/inserting
	public pokedex(String name, String moves, String type, String weakness) {
		super();
		this.name = name;
		this.moves = moves;
		this.type = type;
		this.weakness = weakness;
	}
	
	// Default Constructor
	public pokedex() {
		super();
	}

	// getters and setters
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMoves() {
		return moves;
	}

	public void setMoves(String moves) {
		this.moves = moves;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getWeakness() {
		return weakness;
	}

	public void setWeakness(String weakness) {
		this.weakness = weakness;
	}

	@Override
	public String toString() {
		return "pokedex [id=" + id + ", name=" + name + ", moves=" + moves + ", type=" + type + ", weakness=" + weakness
				+ "]";
	}
	

	@Override
	public int hashCode() {
		return Objects.hash(id, moves, name, type, weakness);
	}

	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		pokedex other = (pokedex) obj;
		return id == other.id && Objects.equals(moves, other.moves) && Objects.equals(name, other.name)
				&& Objects.equals(type, other.type) && Objects.equals(weakness, other.weakness);
	}

	

	
}
