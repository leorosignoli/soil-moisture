package br.edu.uniceub.soilmoisture.controllers;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.edu.uniceub.soilmoisture.dtos.PointInTimeDTO;
import br.edu.uniceub.soilmoisture.service.SensorService;
import io.swagger.annotations.Api;

@RestController
@RequestMapping("/sensor")
@Api(tags = "Back-end sensor endpoints")
public class SensorController {

	@Autowired
	private SensorService service;

	@PostMapping
	public ResponseEntity<String> saveNewEntry(@RequestBody PointInTimeDTO dto) {
		return ResponseEntity.status(HttpStatus.CREATED).body(service.addNewEntry(dto));
	}

	@PostMapping("/withoutTime")
	public ResponseEntity<String> saveNewEntryNoTime(@RequestParam(name = "id") String sensorId,
			@RequestParam(name = "value") String value) {
		PointInTimeDTO dto = new PointInTimeDTO();

		dto.setDateTime(new Date());

		dto.setSensorId(sensorId);
		dto.setValue(Float.parseFloat(value));
		return ResponseEntity.status(HttpStatus.CREATED).body(service.addNewEntry(dto));
	}


}
