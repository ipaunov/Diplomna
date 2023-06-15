package com.diploma.ivan.model.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class ConditionDTO {

    private final UUID id;
    private final String propertyKey;
    private final String propertyValue;

    @JsonCreator
    public ConditionDTO( //
            @JsonProperty("id") UUID id, //
            @JsonProperty("propertyKey") String propertyKey, //
            @JsonProperty("propertyValue") String propertyValue //
    ) {
        this.id = id;
        this.propertyKey = propertyKey;
        this.propertyValue = propertyValue;
    }

    @JsonProperty("id")
    public UUID getId() {
        return id;
    }

    @JsonProperty("propertyKey")
    public String getPropertyKey() {
        return propertyKey;
    }

    @JsonProperty("propertyValue")
    public String getPropertyValue() {
        return propertyValue;
    }
}
