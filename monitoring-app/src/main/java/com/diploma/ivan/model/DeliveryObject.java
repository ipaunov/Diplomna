package com.diploma.ivan.model;

import java.util.Objects;

public class DeliveryObject {


    private final String podName;
    private final String actualValue;
    private final String thresholdValue;
    private final String namespace;
    private final String deployment;
    private final String baseUrl;

    public DeliveryObject(String podName, String actualValue, String thresholdValue, String namespace, String deployment, String baseUrl) {
        this.podName = podName;
        this.actualValue = actualValue;
        this.thresholdValue = thresholdValue;
        this.namespace = namespace;
        this.deployment = deployment;
        this.baseUrl = baseUrl;
    }

    public String getPodName() {
        return podName;
    }

    public String getActualValue() {
        return actualValue;
    }

    public String getThresholdValue() {
        return thresholdValue;
    }

    public String getNamespace() {
        return namespace;
    }

    public String getDeployment() {
        return deployment;
    }

    public String getBaseUrl() {
        return baseUrl;
    }
}
