package com.diploma.ivan.model;

import com.diploma.ivan.model.entity.MonitoringConfigurationEntity;

public class CombinedConfiguration {

    private MonitoringConfigurationEntity monitoringConfiguration;
    private Metric metric;

    public CombinedConfiguration() {

    }

    public CombinedConfiguration(MonitoringConfigurationEntity monitoringConfiguration, Metric metric) {
        this.monitoringConfiguration = monitoringConfiguration;
        this.metric = metric;
    }

    public MonitoringConfigurationEntity getMonitoringConfiguration() {
        return monitoringConfiguration;
    }

    public Metric getMetric() {
        return metric;
    }
}
