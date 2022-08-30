package br.edu.uniceub.soilmoisture.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.uniceub.soilmoisture.entities.PointInTime;

@Repository
public interface SensorRepository extends JpaRepository<PointInTime, UUID> {

}
