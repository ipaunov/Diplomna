package com.diploma.ivan.service;

import com.diploma.ivan.model.dto.MonitoringConfigurationDTO;

import java.util.List;

public interface IConfigurationService {

    MonitoringConfigurationDTO create(MonitoringConfigurationDTO monitoringConfigurationDTO);

    List<MonitoringConfigurationDTO> getAll();

    MonitoringConfigurationDTO getConfigurationWithName(String name);

    MonitoringConfigurationDTO update(String name, MonitoringConfigurationDTO monitoringConfigurationDTO);

    void delete(String name);
}
