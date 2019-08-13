package br.com.ramon.gra.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ramon.gra.model.Movie;


public interface MovieRepository extends JpaRepository<Movie, Long> {
	
}
