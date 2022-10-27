package br.edu.uniceub.soilmoisture.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.edu.uniceub.soilmoisture.dtos.PointInTimeDTO;
import br.edu.uniceub.soilmoisture.entities.Sensor;

@Service
public interface SensorService {

	String addNewEntry(PointInTimeDTO dto);

	/**
	 * Finds all entries in the database given the time period.
	 * 
	 * @param from
	 * @param to
	 * @return
	 */
	List<Sensor> findAll(String from, String to);

	List<String> listSensors();

}
