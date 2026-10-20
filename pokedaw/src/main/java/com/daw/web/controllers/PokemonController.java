package com.daw.web.controllers;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.daw.persistence.entities.Pokemon;
import com.daw.persistence.entities.enums.Tipo;
import com.daw.services.PokemonService;
import com.daw.services.exceptions.PokemonException;
import com.daw.services.exceptions.PokemonNotFoundException;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/pokemons")
@RequiredArgsConstructor
public class PokemonController {
	private final PokemonService pokemonService;
	
	@GetMapping
	public List<Pokemon> list(){
		return this.pokemonService.findAll();
	}
	
	@GetMapping("/{idPokemon}")
	public ResponseEntity<?> findById(@PathVariable long idPokemon){
		try {
			return ResponseEntity.ok(this.pokemonService.findById(idPokemon));
		}catch(PokemonNotFoundException pk) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(pk.getMessage());
		}
	}
	
	@PostMapping
	public ResponseEntity<?> create(@RequestBody Pokemon pokemon){
		try {
			return ResponseEntity.status(HttpStatus.CREATED).body(this.pokemonService.create(pokemon));
		}
		catch(PokemonException pk) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(pk.getMessage());
		}
	}
	
	@PutMapping("/{idPokemon}")
	public ResponseEntity<?> update(@PathVariable long idPokemon, @RequestBody Pokemon pokemon){
		try {
			return ResponseEntity.ok(this.pokemonService.update(idPokemon, pokemon));
		}catch(PokemonException pk) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(pk.getMessage());
		}
	}
	
	@DeleteMapping("/{idPokemon}")
	public ResponseEntity<?> deleteById(@PathVariable long idPokemon){
		try {
			this.pokemonService.deleteById(idPokemon);
			return ResponseEntity.ok().build();
		}catch(PokemonNotFoundException pk) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(pk.getMessage());
		}
	}
	
	@GetMapping("/pokedex")
	public ResponseEntity<List<Pokemon>> findByNumeroPokedex(@RequestParam int numeroPokedex){
		return ResponseEntity.ok(this.pokemonService.findByNumeroPokedex(numeroPokedex));
	}
	
	@GetMapping("/fecha")
	public ResponseEntity<List<Pokemon>> findByFechaCapturaBetween(
	        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaInicio,
	        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaFin) {
	    return ResponseEntity.ok(this.pokemonService.findByFechaCapturaBetween(fechaInicio, fechaFin));
	}
	
	@GetMapping("/tipo")
	public ResponseEntity<List<Pokemon>> findByTipo(@RequestParam Tipo tipo) {
	    return ResponseEntity.ok(this.pokemonService.findByTipo(tipo));
	}
}
