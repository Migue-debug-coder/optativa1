package com.daw.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.daw.persistence.entities.Pokemon;
import com.daw.persistence.entities.enums.Capturado;
import com.daw.persistence.repositories.PokemonRepository;
import com.daw.services.exceptions.PokemonException;
import com.daw.services.exceptions.PokemonNotFoundException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PokemonService {
	
	private final PokemonRepository pokemonRepository;
	
	public List<Pokemon> findAll(){
		return this.pokemonRepository.findAll();
	}
	
	public Pokemon findById(long idPokemon) {
		if(!this.pokemonRepository.existsById(idPokemon)) {
			throw new PokemonNotFoundException(String.format("El pokemon con id %d no está registrado. ",idPokemon));
		}
		
		return this.pokemonRepository.findById(idPokemon).get();
	}
	
	public Pokemon create(Pokemon pokemon) {
		if(pokemon.getFechaCaptura() != null) {
			throw new PokemonException(String.format("La fecha de captura del pokemon no se puede modificar"));
		}
		if(pokemon.getTipo1() == pokemon.getTipo2()) {
			throw new PokemonException(String.format("Un pokemon no puede tener 2 tipos idénticos"));
		}
		if(pokemon.getCapturado() == null) {
			pokemon.setCapturado(Capturado.POKEBALL);
		}
		
		return this.pokemonRepository.save(pokemon);
	}
	
	public Pokemon update(long idPokemon, Pokemon pokemon) {
		if(pokemon.getId() != idPokemon) {
			throw new PokemonException("El id del body y el id del path no coinciden. ");
		}
		if(!this.pokemonRepository.existsById(idPokemon)) {
			throw new PokemonNotFoundException(String.format("El pokemon con id %d no está registrado. ",idPokemon));
		}
		if(pokemon.getFechaCaptura() != null) {
			throw new PokemonException(String.format("La fecha de captura del pokemon no se puede modificar"));
		}
		if(pokemon.getTipo1() == pokemon.getTipo2()) {
			throw new PokemonException(String.format("Un pokemon no puede tener 2 tipos idénticos"));
		}
		if(pokemon.getCapturado() == null) {
			pokemon.setCapturado(Capturado.POKEBALL);
		}
		
		return this.pokemonRepository.save(pokemon);
	}
	
	public void deleteById(long idPokemon) {
		if(!this.pokemonRepository.existsById(idPokemon)) {
			throw new PokemonNotFoundException(String.format("El pokemon con id %d no está registrado. ",idPokemon));
		}
		
		this.pokemonRepository.deleteById(idPokemon);
	}
}
