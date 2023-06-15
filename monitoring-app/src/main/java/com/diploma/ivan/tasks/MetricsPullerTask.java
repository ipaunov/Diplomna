package com.diploma.ivan.tasks;

import com.diploma.ivan.kafka.Producer;
import com.diploma.ivan.kafka.Topics;
import com.diploma.ivan.model.CombinedConfiguration;
import com.diploma.ivan.model.Metric;
import com.diploma.ivan.model.entity.MonitoringConfigurationEntity;
import com.diploma.ivan.repository.MonitoringConfigurationRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public class MetricsPullerTask implements ScheduledTask {

    private static final String PATH_FORMAT = "%s/apis/metrics.k8s.io/v1beta1/namespaces/%s/pods?labelSelector=app=%s";
    private final RestTemplate restTemplate;
    private final Producer producer;

    MonitoringConfigurationRepository monitoringConfigurationRepository;

    public MetricsPullerTask(MonitoringConfigurationRepository monitoringConfigurationRepository, Producer producer) {
        this.monitoringConfigurationRepository = monitoringConfigurationRepository;
        this.restTemplate = new RestTemplate();
        this.producer = producer;
    }

    @Scheduled(fixedDelay = 5000)
    @Override
    public void execute() {
        List<MonitoringConfigurationEntity> configurationEntityList = monitoringConfigurationRepository.findAll();

        configurationEntityList.forEach(this::processMetrics);
    }

    private void processMetrics(MonitoringConfigurationEntity monitoringConfigurationEntity) {
        Metric metric = restTemplate.getForEntity(String.format(PATH_FORMAT, monitoringConfigurationEntity.getBaseUrl(), monitoringConfigurationEntity.getNamespace(), monitoringConfigurationEntity.getLabel()), Metric.class).getBody();


        producer.sendToKafka( //
                new CombinedConfiguration(monitoringConfigurationEntity, metric), //
                Topics.METRICS //
        );
    }
}
