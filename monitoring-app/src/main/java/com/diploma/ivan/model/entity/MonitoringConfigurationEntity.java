package com.diploma.ivan.model.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.UUID;

@Entity
@Table(name = "configurations")
public class MonitoringConfigurationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private UUID id;

    @NotBlank
    @Column(name = "name")
    private String name;

    @NotBlank
    @Column(name = "base_url")
    private String baseUrl;

    @NotBlank
    @Column(name = "namespace")
    private String namespace;

    @NotBlank
    @Column(name = "deployment")
    private String deployment;

    @NotBlank
    @Column(name = "label")
    private String label;

    @OneToOne(cascade = CascadeType.ALL)
    private ConditionEntity condition;

    @OneToOne(cascade = CascadeType.ALL)
    private MailActionEntity action;

    public MonitoringConfigurationEntity() {

    }

    public MonitoringConfigurationEntity( //
            UUID id, //
            String name, //
            String baseUrl, //
            String namespace, //
            String deployment, //
            String label, //
            ConditionEntity condition, //
            MailActionEntity action //
    ) {
        this.id = id;
        this.name = name;
        this.baseUrl = baseUrl;
        this.namespace = namespace;
        this.deployment = deployment;
        this.label = label;
        this.condition = condition;
        this.action = action;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public String getNamespace() {
        return namespace;
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }

    public String getDeployment() {
        return deployment;
    }

    public void setDeployment(String deployment) {
        this.deployment = deployment;
    }
    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public ConditionEntity getConditionEntity() {
        return condition;
    }

    public void setConditionEntity(ConditionEntity condition) {
        this.condition = condition;
    }

    public MailActionEntity getActionEntity() {
        return action;
    }

    public void setActionEntity(MailActionEntity action) {
        this.action = action;
    }
}
