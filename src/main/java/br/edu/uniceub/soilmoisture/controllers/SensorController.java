package br.edu.uniceub.soilmoisture.controllers;

import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.edu.uniceub.soilmoisture.dtos.PointInTimeDTO;
import br.edu.uniceub.soilmoisture.service.SensorService;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/sensor")
public class SensorController {

	@Autowired
	private SensorService service;

	@PostMapping
	public ResponseEntity<UUID> saveNewEntry(@RequestBody PointInTimeDTO dto) {
		return ResponseEntity.status(HttpStatus.CREATED).body(service.addNewEntry(dto));
	}

	@GetMapping
	@ApiOperation(notes = "Date formate: yyyy-MM-dd HH:mm", value = "Find All entries, if date is value, returns all of the data.")
	public ResponseEntity<Set<PointInTimeDTO>> findAll(@RequestParam(required = false) String from,
			@RequestParam(required = false) String to) {
		return ResponseEntity.status(HttpStatus.OK).body(service.findAll(from, to));
	}

}
