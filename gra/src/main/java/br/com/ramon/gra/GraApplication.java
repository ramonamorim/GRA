package br.com.ramon.gra;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class GraApplication {

	public static void main(String[] args) {
		SpringApplication.run(GraApplication.class, args);
	}

}
