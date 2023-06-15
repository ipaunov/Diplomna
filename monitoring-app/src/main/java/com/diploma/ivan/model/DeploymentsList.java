package com.diploma.ivan.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DeploymentsList implements Serializable {

    private List<Deployment> items;

    public DeploymentsList() {

    }

    public DeploymentsList(List<Deployment> items) {
        this.items = items;
    }

    public List<Deployment> getItems() {
        return items;
    }
}
