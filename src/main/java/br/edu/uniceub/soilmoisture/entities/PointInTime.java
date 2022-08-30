package br.edu.uniceub.soilmoisture.entities;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class PointInTime {

	@Id
	private UUID id;

	@Column
	private String sensorId;

	@Column
	private LocalDateTime time;

	@Column
	private Float value;

}
