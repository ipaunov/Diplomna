package com.diploma.ivan.model.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class MailAction {

    private final UUID id;
    private final String destination;


    @JsonCreator
    public MailAction( //
            @JsonProperty("id") UUID id, //
            @JsonProperty("destination") String destination //
    ) {
        this.id = id;
        this.destination = destination;
    }

    @JsonProperty("id")
    public UUID getId() {
        return id;
    }

    @JsonProperty("destination")
    public String getDestination() {
        return destination;
    }

}
