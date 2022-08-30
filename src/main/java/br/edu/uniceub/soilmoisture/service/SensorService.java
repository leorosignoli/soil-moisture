package br.edu.uniceub.soilmoisture.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import br.edu.uniceub.soilmoisture.dtos.PointInTimeDTO;

@Service
public interface SensorService {

	UUID addNewEntry(PointInTimeDTO dto);

}
