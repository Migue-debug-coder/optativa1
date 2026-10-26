package com.daw.persistence.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.daw.persistence.entities.Pokemon;
import com.daw.persistence.entities.enums.Tipo;

public interface PokemonRepository extends JpaRepository<Pokemon, Long>{

	
	List<Pokemon>findByNumeroPokedex(int numPokedex);
	
	List<Pokemon> findByFechaCapturaBetween(LocalDate fechaInicio, LocalDate fechaFinal);
	
	List<Pokemon>findByTipo1OrTipo2(Tipo tipo1);
	
}
