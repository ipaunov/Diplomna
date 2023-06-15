package com.diploma.ivan.model.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class MonitoringConfigurationDTO {

    private final UUID id;
    private final String name;
    private final String baseUrl;
    private final String namespace;
    private final String deployment;
    private final String label;
    private final ConditionDTO conditionDTO;
    private final MailAction actionDTO;

    @JsonCreator
    public MonitoringConfigurationDTO( //
            @JsonProperty("id") UUID id, //
            @JsonProperty("name") String name, //
            @JsonProperty("baseUrl") String baseUrl, //
            @JsonProperty("namespace") String namespace, //
            @JsonProperty("deployment") String deployment, //
            @JsonProperty("label") String label, //
            @JsonProperty("condition") ConditionDTO conditionDTO, //
            @JsonProperty("action") MailAction actionDTO //
    ) {
        this.id = id;
        this.name = name;
        this.baseUrl = baseUrl;
        this.namespace = namespace;
        this.deployment = deployment;
        this.label = label;
        this.conditionDTO = conditionDTO;
        this.actionDTO = actionDTO;
    }

    @JsonProperty("id")
    public UUID getId() {
        return id;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("baseUrl")
    public String getBaseUrl() {
        return baseUrl;
    }

    @JsonProperty("namespace")
    public String getNamespace() {
        return namespace;
    }

    @JsonProperty("deployment")
    public String getDeployment() {
        return deployment;
    }

    @JsonProperty("label")
    public String getLabel() {
        return label;
    }

    @JsonProperty("condition")
    public ConditionDTO getCondition() {
        return conditionDTO;
    }

    @JsonProperty("action")
    public MailAction getAction() {
        return actionDTO;
    }
}
