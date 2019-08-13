package br.com.ramon.gra.model;

import lombok.Data;

@Data
public class ProducerMinMax {

	public ProducerMinMax(String producer, Integer interval, Integer prevWin, Integer follWin) {
		this.producer = producer;
		this.interval = interval;
		this.previousWin = prevWin;
		this.followingWin = follWin;

	}

	private String producer;
	private Integer interval;
	private Integer previousWin;
	private Integer followingWin;
}
