package br.edu.uniceub.soilmoisture.dtos;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class Sensor {

	private String id;
	private List<Use> uses = new ArrayList<>();

	public Sensor(String id, Use use) {
		this.id = id;
		uses.add(use);
	}

	public Sensor(String id) {
		this.id = id;
	}

	@Data
	@AllArgsConstructor
	public static class Use {
		private Date time;
		private Float value;
	}

}
