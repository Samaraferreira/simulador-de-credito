package com.simulator.infrastructure.persistence;

import com.simulator.infrastructure.persistence.entity.SimulationEntity;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

@EnableScan
public interface SimulationRepository extends CrudRepository<SimulationEntity, String> {
}
