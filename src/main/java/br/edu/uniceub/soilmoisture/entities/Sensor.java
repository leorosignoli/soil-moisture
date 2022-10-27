package br.edu.uniceub.soilmoisture.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Sensor {

	@Id
	@Column
	private String sensorId;

	@Column
	private Integer minimumValue;
	@Column
	private Integer maximumValue;
	
	@Column
	@OneToMany(cascade = CascadeType.ALL)
	private Set<Entry> entries= new HashSet<>();


	public Sensor(String sensorId) {
		super();
		this.sensorId = sensorId;
	}

	
}
