package com.diploma.ivan.service;

import com.diploma.ivan.model.Deployment;

import java.util.List;

public interface IDeploymentService {

    List<Deployment> getDeployments(String namespace);
    Deployment getDeployment(String namespace, String deployment);
    Deployment scaleDeployment(String namespace, String deploymentString, Deployment deployment);
}
