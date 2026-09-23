package com.daw;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/holamundo")
public class HolaMundoController {
	
	@GetMapping
	public String saludar() {
		return "HolaMundo";
	}
	
	/*
	 * El Request Param se utiliza cuando tengamos cualquier método en el que tengamos
	 * que mandar parámetros al servidor
	 * 
	 * Ejemplo:
	 * 		localhost:8080/books/buscarPorTitulo?titulo=
	 */
	@GetMapping("/saludo")
	public String saludar2(@RequestParam(defaultValue = "Anónimo") String nombre) {
		return "Hola " + nombre;
	}
	
	/*
	 * El PathVariable se utiliza exclusivamente para IDs
	 */
	
	@GetMapping("/books/{booksId}")
	public String libros(@PathVariable long id) {
		return "Aquí tienes el libro con el id " + id;
	}
}
