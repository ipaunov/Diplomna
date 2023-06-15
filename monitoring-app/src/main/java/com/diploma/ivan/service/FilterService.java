package com.diploma.ivan.service;

import com.diploma.ivan.kafka.Topics;
import com.diploma.ivan.model.CombinedConfiguration;
import com.diploma.ivan.model.DeliveryObject;
import com.diploma.ivan.model.Metric;
import com.diploma.ivan.util.Converter;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

import static com.diploma.ivan.service.MailService.executeMail;
import static com.diploma.ivan.util.FilteringUtils.checkIfCrossesThreshold;
import static java.util.Objects.isNull;

@Component
public class FilterService {

    private static final String BASE_URL = "http://localhost:9001";
    private static final String GROUP_ID = "metrics";

    @KafkaListener(topics = Topics.METRICS, groupId = GROUP_ID, autoStartup = "true")
    public void processConfigurationsWithMetrics(String message, Acknowledgment acknowledgment) {
        CombinedConfiguration combinedConfiguration = Converter.convertMessageToCombinedConfiguration(message);

        for (Metric.Pod pod : combinedConfiguration.getMetric().getItems()) {
            DeliveryObject deliveryObject = checkIfCrossesThreshold(pod.getMetadata().getName(), pod.getContainers(), combinedConfiguration.getMonitoringConfiguration().getConditionEntity(), combinedConfiguration.getMonitoringConfiguration().getNamespace(), combinedConfiguration.getMonitoringConfiguration().getDeployment(), BASE_URL);
            if (!isNull(deliveryObject)) {
                executeMail(deliveryObject, combinedConfiguration.getMonitoringConfiguration().getActionEntity().getDestination());
            }
        }

        acknowledgment.acknowledge();
    }

}
