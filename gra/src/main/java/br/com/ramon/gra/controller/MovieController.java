package br.com.ramon.gra.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ramon.gra.repository.MovieRepository;

@RestController
public class MovieController {

	@Autowired
	private MovieRepository movieRep;

	
	@GetMapping("/movies-list")
	ResponseEntity<Object> allMovies() {
		return ResponseEntity.status(HttpStatus.OK).body(movieRep.findAll());
	}

	

}
