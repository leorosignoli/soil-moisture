package br.edu.uniceub.soilmoisture.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.edu.uniceub.soilmoisture.entities.Sensor;
import br.edu.uniceub.soilmoisture.service.SensorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/front-end")
@Api(tags = "Front-end endpoints")
public class FrontEndSensorController {

	// FRONT END ENDPOINTS

	@Autowired
	private SensorService service;

	@GetMapping("/findAll")
	@ApiOperation(notes = "Date formate: yyyy-MM-dd HH:mm", value = "Find All entries, if date is value, returns all of the data.")
	public ResponseEntity<List<Sensor>> findAll(@RequestParam(required = false) String from,
			@RequestParam(required = false) String to) {
		return ResponseEntity.status(HttpStatus.OK).body(service.findAll(from, to));
	}

	@GetMapping(path = "/listSensorNames")
	@ApiOperation(value = "List all sensors")
	public ResponseEntity<List<String>> getSensorList() {
		return ResponseEntity.ok(service.listSensors());
	}

	@GetMapping(path = "/{sensorName}")
	@ApiOperation(value = "Finds all entries for the sensor with the given name.")
	public ResponseEntity<Sensor> findOne(@PathVariable String sensorName) throws NotFoundException {
		return ResponseEntity.ok(service.findOne(sensorName));
	}

	@PatchMapping("/{sensorName}")
	@ApiOperation(value = "Define acceptable moisture range.")
	public ResponseEntity<Sensor> setExpectedMoistureRange(@PathVariable String sensorName,
			@RequestParam Integer minimumMoisture, @RequestParam Integer maximumMoisture) throws NotFoundException {
		service.setMoisture(sensorName, minimumMoisture, maximumMoisture);
		return ResponseEntity.ok().build();
	}

	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<Void> handleNotFound(NotFoundException ex) {
		return ResponseEntity.notFound().build();
	}
}
