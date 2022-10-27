package br.edu.uniceub.soilmoisture.service;

import java.util.List;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
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

	Sensor findOne(String id) throws NotFoundException;

	void setMoisture(String id, Integer min, Integer max) throws NotFoundException;

}
