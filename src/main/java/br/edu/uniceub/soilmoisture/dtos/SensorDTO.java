package br.edu.uniceub.soilmoisture.dtos;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class SensorDTO {

	private String id;
	private List<Use> uses = new ArrayList<>();

	public SensorDTO(String id, Use use) {
		this.id = id;
		uses.add(use);
	}

	public SensorDTO(String id) {
		this.id = id;
	}

	@Data
	@AllArgsConstructor
	public static class Use {
		private Date time;
		private Float value;
	}

}
