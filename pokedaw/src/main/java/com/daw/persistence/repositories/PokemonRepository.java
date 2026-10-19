package com.daw.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.daw.persistence.entities.Pokemon;

public interface PokemonRepository extends JpaRepository<Pokemon, Long>{

}
