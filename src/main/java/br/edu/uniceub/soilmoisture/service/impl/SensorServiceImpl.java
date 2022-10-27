package br.edu.uniceub.soilmoisture.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.uniceub.soilmoisture.dtos.PointInTimeDTO;
import br.edu.uniceub.soilmoisture.entities.Entry;
import br.edu.uniceub.soilmoisture.entities.Sensor;
import br.edu.uniceub.soilmoisture.exceptions.InvalidDateTime;
import br.edu.uniceub.soilmoisture.repositories.SensorRepository;
import br.edu.uniceub.soilmoisture.service.SensorService;

@Service
public class SensorServiceImpl implements SensorService {

	private SensorRepository sensorRepo;

	private Logger logger = LoggerFactory.logger(getClass());

	@Autowired
	public void setSensor(SensorRepository repo) {
		this.sensorRepo = repo;
	}

	@Override
	public String addNewEntry(PointInTimeDTO dto) {

		Sensor sensor = sensorRepo.findById(dto.getSensorId()).orElse(new Sensor(dto.getSensorId()));
		sensor.getEntries().add(new Entry(dto.getDateTime(), dto.getValue()));
		sensorRepo.save(sensor);
		return sensor.getSensorId();
	}

	@Override
	public List<Sensor> findAll(String from, String to) {
		try {

			// If any of these is null, just return all the data.
			if ((from == null) || (to == null)) {
				logger.info("Date not specified, returning all the data.");
				List<Sensor> points = sensorRepo.findAll();
				return points;
			} else {
				Date fromDate = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(from);
				Date toDate = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(to);
				List<Sensor> sensors = sensorRepo.findAllByEntries_TimeBetween(fromDate, toDate);
				return sensors;
			}
		} catch (ParseException e) {
			throw new InvalidDateTime();
		}
	}

	@Override
	public List<String> listSensors() {

		return sensorRepo.findDistinctSensors();
	}



}
