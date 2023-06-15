package com.diploma.ivan.repository;

import com.diploma.ivan.model.entity.MonitoringConfigurationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface MonitoringConfigurationRepository extends JpaRepository<MonitoringConfigurationEntity, UUID> {

    Optional<MonitoringConfigurationEntity> findByName(String name);
}
