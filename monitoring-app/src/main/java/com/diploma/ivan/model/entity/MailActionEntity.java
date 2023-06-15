package com.diploma.ivan.model.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.UUID;

@Entity
@Table(name = "actions")
public class MailActionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private UUID id;

    @NotBlank
    @Column(name = "destination")
    private String destination;

    @OneToOne
    @JoinColumn(name = "configuration_id")
    private MonitoringConfigurationEntity monitoringConfiguration;

    public MailActionEntity() {

    }

    public MailActionEntity( //
            UUID id, //
            String type, //
            String destination //
    ) {
        this.id = id;
        this.destination = destination;
    }

    public UUID getId() {
        return id;
    }
    public String getDestination() {
        return destination;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }
}
