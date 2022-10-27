package br.edu.uniceub.soilmoisture.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.edu.uniceub.soilmoisture.entities.Sensor;

@Repository
public interface SensorRepository extends JpaRepository<Sensor, String> {

	List<Sensor> findAllByEntries_TimeBetween(Date publicationTimeStart, Date publicationTimeEnd);
	
	@Query("SELECT DISTINCT sensorId FROM Sensor")
	List<String> findDistinctSensors();

}
