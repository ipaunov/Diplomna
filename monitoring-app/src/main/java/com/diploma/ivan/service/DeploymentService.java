package com.diploma.ivan.service;

import com.diploma.ivan.model.Deployment;
import com.diploma.ivan.model.DeploymentsList;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DeploymentService implements IDeploymentService {

    private static final String BASE_URL = "http://localhost:8081";
    private static final String GET_ALL_DEPLOYMENTS_IN_NAMESPACE = "%s/apis/apps/v1/namespaces/%s/deployments";
    private static final String GET_DEPLOYMENT = "%s/apis/apps/v1/namespaces/%s/deployments/%s/scale";

    private final RestTemplate restTemplate;

    public DeploymentService() {
        this.restTemplate = new RestTemplate();
    }

    @Override
    public List<Deployment> getDeployments(String namespace) {
        DeploymentsList deployments = restTemplate.getForEntity(String.format(GET_ALL_DEPLOYMENTS_IN_NAMESPACE, BASE_URL, namespace), DeploymentsList.class).getBody();
        return deployments.getItems();
    }

    @Override
    public Deployment getDeployment(String namespace, String deployment) {
        return restTemplate.getForEntity(String.format(GET_DEPLOYMENT, BASE_URL, namespace, deployment), Deployment.class).getBody();
    }

    @Override
    public Deployment scaleDeployment(String namespace, String deploymentString, Deployment deployment) {
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

        return restTemplate.exchange(String.format(GET_DEPLOYMENT, BASE_URL, namespace, deploymentString), HttpMethod.PUT, new HttpEntity<>(deployment), Deployment.class).getBody();
    }
}
