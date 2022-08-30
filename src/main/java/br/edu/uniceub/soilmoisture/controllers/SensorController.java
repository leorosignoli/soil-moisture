package br.edu.uniceub.soilmoisture.controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.uniceub.soilmoisture.dtos.PointInTimeDTO;
import br.edu.uniceub.soilmoisture.service.SensorService;

@RestController
@RequestMapping("/sensor")
public class SensorController {

	@Autowired
	private SensorService service;

	@PostMapping
	public ResponseEntity<UUID> saveNewEntry(@RequestBody PointInTimeDTO dto) {
		return ResponseEntity.status(HttpStatus.CREATED).body(service.addNewEntry(dto));
	}

}
