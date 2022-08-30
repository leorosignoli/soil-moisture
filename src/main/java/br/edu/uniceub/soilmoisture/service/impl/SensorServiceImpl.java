package br.edu.uniceub.soilmoisture.service.impl;

import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.uniceub.soilmoisture.dtos.PointInTimeDTO;
import br.edu.uniceub.soilmoisture.entities.PointInTime;
import br.edu.uniceub.soilmoisture.repositories.SensorRepository;
import br.edu.uniceub.soilmoisture.service.SensorService;

@Service
public class SensorServiceImpl implements SensorService {

	private SensorRepository sensorRepo;

	@Autowired
	public void setSensor(SensorRepository repo) {
		this.sensorRepo = repo;
	}

	@Override
	public UUID addNewEntry(PointInTimeDTO dto) {
		PointInTime pointInTime = new PointInTime();
		BeanUtils.copyProperties(dto, pointInTime);
		sensorRepo.save(pointInTime);
		return pointInTime.getId();
	}

}
