package com.daw.persistence.entities;

import java.time.LocalDate;

import com.daw.persistence.entities.enums.Capturado;
import com.daw.persistence.entities.enums.Tipo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "pokemon")
@Getter
@Setter
@NoArgsConstructor
public class Pokemon {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "numero_pokedex")
	private int numeroPokedex;
	
	@Column(name = "nombre")
	private String titulo;
	private Tipo tipo1;
	private Tipo tipo2;
	
	@Column(name = "fecha_captura")
	private LocalDate fechaCaptura;
	private Capturado capturado;
}
