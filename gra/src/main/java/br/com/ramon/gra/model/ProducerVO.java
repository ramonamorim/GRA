package br.com.ramon.gra.model;

import java.util.List;

import lombok.Data;

@Data
public class ProducerVO {

	private List<ProducerMinMax> min;
	private List<ProducerMinMax> max;
}
