package com.diploma.ivan.controller;

import com.diploma.ivan.model.Deployment;
import com.diploma.ivan.service.IDeploymentService;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping(
        path = "mitigate/v1/deployments"
)
public class DeploymentsController {

    private static final String NAMESPACE_VARIABLE = "namespace";
    private static final String DEPLOYMENT_VARIABLE = "deployment";

    private IDeploymentService deploymentService;

    public DeploymentsController(IDeploymentService deploymentService) {
        this.deploymentService = deploymentService;
    }

    @GetMapping(path = "/{namespace}")
    public ResponseEntity<List<Deployment>> getAllDeployments( //
            @PathVariable(NAMESPACE_VARIABLE) String namespace //
    ) {
        return status(OK).body(deploymentService.getDeployments(namespace));
    }

    @GetMapping(path = "/{namespace}/{deployment}")
    public ModelAndView getDeployment( //
            Model model, //
            @PathVariable(NAMESPACE_VARIABLE) String namespace, //
            @PathVariable(DEPLOYMENT_VARIABLE) String deployment //
    ) {
        Deployment deploymentObject = deploymentService.getDeployment(namespace, deployment);
        model.addAttribute("deployment", deploymentObject);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("deployment");

        return modelAndView;
    }

    @PutMapping(path = "/{namespace}/{deployment}")
    public ResponseEntity<Deployment> scaleDeployment(
            @PathVariable(NAMESPACE_VARIABLE) String namespace, //
            @PathVariable(DEPLOYMENT_VARIABLE) String deploymentString, //
            @RequestBody Deployment deployment //
    ) {
        return status(OK).body(deploymentService.scaleDeployment(namespace, deploymentString, deployment));
    }
}
