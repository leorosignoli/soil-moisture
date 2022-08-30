package br.edu.uniceub.soilmoisture.dtos;

import lombok.Data;

@Data
public class PointInTimeDTO {

	private String sensorId;
	private String dateTime;
	private Float value;

}
