package com.diploma.ivan.model.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.UUID;

@Entity
@Table(name = "conditions")
public class ConditionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private UUID id;

    @NotBlank
    @Column(name = "property_key")
    private String propertyKey;

    @NotBlank
    @Column(name = "property_value")
    private String propertyValue;

    @OneToOne
    @JoinColumn(name = "configuration_id")
    private MonitoringConfigurationEntity monitoringConfiguration;

    public ConditionEntity() {

    }

    public ConditionEntity( //
            UUID id, //
            String propertyKey, //
            String propertyValue //
    ) {
        this.id = id;
        this.propertyKey = propertyKey;
        this.propertyValue = propertyValue;
    }

    public UUID getId() {
        return id;
    }
    public String getPropertyKey() {
        return propertyKey;
    }
    public String getPropertyValue() {
        return propertyValue;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setPropertyKey(String propertyKey) {
        this.propertyKey = propertyKey;
    }

    public void setPropertyValue(String propertyValue) {
        this.propertyValue = propertyValue;
    }
}
