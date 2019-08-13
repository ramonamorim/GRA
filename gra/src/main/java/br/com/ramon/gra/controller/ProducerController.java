package br.com.ramon.gra.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ramon.gra.model.ProducerVO;
import br.com.ramon.gra.service.ProducersService;

@RestController
public class ProducerController {

	@Autowired
	private ProducersService producersService;

	@GetMapping("/producers-result")
	ResponseEntity<Object> producersMinMaxResult() {
		ProducerVO producerMinMax = producersService.getProducerResult();
		return ResponseEntity.status(HttpStatus.OK).body(producerMinMax);

	}

}
