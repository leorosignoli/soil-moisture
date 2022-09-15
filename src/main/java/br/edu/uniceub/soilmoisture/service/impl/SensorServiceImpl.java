package br.edu.uniceub.soilmoisture.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.jboss.logging.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.uniceub.soilmoisture.dtos.PointInTimeDTO;
import br.edu.uniceub.soilmoisture.dtos.Sensor;
import br.edu.uniceub.soilmoisture.dtos.Sensor.Use;
import br.edu.uniceub.soilmoisture.entities.PointInTime;
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
	public UUID addNewEntry(PointInTimeDTO dto) {
		logger.info("Saving new entity.");
		PointInTime pointInTime = new PointInTime();
		BeanUtils.copyProperties(dto, pointInTime);
		pointInTime.setTime(dto.getDateTime());
		sensorRepo.save(pointInTime);
		logger.info("Successfully saved: " + pointInTime.getId().toString());
		return pointInTime.getId();
	}

	@Override
	public List<PointInTimeDTO> findAll(String from, String to) {
		try {

			// If any of these is null, just return all the data.
			if ((from == null) || (to == null)) {
				logger.info("Date not specified, returning all the data.");
				List<PointInTime> points = sensorRepo.findAll();
				return points.stream().map(entry -> {
					PointInTimeDTO dto = new PointInTimeDTO();
					BeanUtils.copyProperties(entry, dto);
					dto.setDateTime(entry.getTime());
					return dto;
				}).collect(Collectors.toList());
			} else {
				Date fromDate = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(from);
				Date toDate = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(to);
				List<PointInTime> pointInTime = sensorRepo.findAllByTimeBetween(fromDate, toDate);
				return pointInTime.stream().map(entry -> {
					PointInTimeDTO dto = new PointInTimeDTO();
					BeanUtils.copyProperties(entry, dto);
					dto.setDateTime(entry.getTime());
					return dto;
				}).collect(Collectors.toList());
			}
		} catch (ParseException e) {
			throw new InvalidDateTime();
		}
	}

	@Override
	public List<Sensor> findFiltered(String from, String to) {

		List<PointInTime> points = null;
		if ((from == null) || (to == null)) {
			points = sensorRepo.findAll();
		} else {
			try {
				Date fromDate = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(from);
				Date toDate = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(to);
				points = sensorRepo.findAllByTimeBetween(fromDate, toDate);
			} catch (ParseException e) {
				throw new InvalidDateTime();
			}

		}
		return filter(points);

	}

	private List<Sensor> filter(List<PointInTime> req) {

		List<Sensor> list = Stream.of(req).collect(ArrayList<Sensor>::new, (sensors, rows) -> {
			rows.forEach(row -> {

				if (sensors.stream().noneMatch((sensor) -> {
					boolean evaluation = sensor.getId().equals(row.getSensorId());

					if (evaluation)
						sensor.getUses().add(new Use(row.getTime(), row.getValue()));
					return evaluation;
				})) {
					sensors.add(new Sensor(row.getSensorId(), new Use(row.getTime(), row.getValue())));
				}

			});
		}, (finalSet, partialSet) -> {
			finalSet.addAll(partialSet);
		});

		return list;
	}

	@Override
	public List<String> listSensors() {

		return sensorRepo.findDistinctSensors();
	}

}
