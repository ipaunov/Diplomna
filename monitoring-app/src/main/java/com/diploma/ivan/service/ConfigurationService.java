package com.diploma.ivan.service;

import com.diploma.ivan.model.dto.MonitoringConfigurationDTO;
import com.diploma.ivan.model.entity.ConditionEntity;
import com.diploma.ivan.model.entity.MailActionEntity;
import com.diploma.ivan.model.entity.MonitoringConfigurationEntity;
import com.diploma.ivan.repository.MonitoringConfigurationRepository;
import com.diploma.ivan.util.Converter;

import java.util.List;
import java.util.stream.Collectors;

import static com.diploma.ivan.util.Converter.convertToDto;
import static com.diploma.ivan.util.Converter.convertToEntity;

public class ConfigurationService implements IConfigurationService {

    private final MonitoringConfigurationRepository monitoringConfigurationRepository;

    public ConfigurationService(MonitoringConfigurationRepository monitoringConfigurationRepository) {
        this.monitoringConfigurationRepository = monitoringConfigurationRepository;
    }

    @Override
    public MonitoringConfigurationDTO create(MonitoringConfigurationDTO monitoringConfigurationDTO) {
        return convertToDto(monitoringConfigurationRepository.saveAndFlush(convertToEntity(monitoringConfigurationDTO)));
    }

    @Override
    public List<MonitoringConfigurationDTO> getAll() {
        return monitoringConfigurationRepository.findAll().stream().map(Converter::convertToDto).collect(Collectors.toList());
    }

    @Override
    public MonitoringConfigurationDTO getConfigurationWithName(String name) {
        // TODO custom exception

        return convertToDto(monitoringConfigurationRepository.findByName(name).orElseThrow(() -> new RuntimeException("Test")));
    }

    @Override
    public MonitoringConfigurationDTO update(String name, MonitoringConfigurationDTO monitoringConfigurationDTO) {
        // TODO custom exception
        MonitoringConfigurationEntity toBeUpdated = monitoringConfigurationRepository.findByName(name).orElseThrow(() -> new RuntimeException("Test"));
        MonitoringConfigurationEntity newConfiguration = convertToEntity(monitoringConfigurationDTO);
        newConfiguration.setId(toBeUpdated.getId());

        return convertToDto(monitoringConfigurationRepository.saveAndFlush(newConfiguration));
    }

    @Override
    public void delete(String name) {
        // TODO custom exception and validation
        MonitoringConfigurationEntity toBeDeleted = monitoringConfigurationRepository.findByName(name).orElseThrow(() -> new RuntimeException("Test"));

        monitoringConfigurationRepository.delete(toBeDeleted);
    }
}
