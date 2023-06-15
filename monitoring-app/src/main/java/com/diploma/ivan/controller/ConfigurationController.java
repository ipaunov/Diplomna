package com.diploma.ivan.controller;

import com.diploma.ivan.model.dto.MonitoringConfigurationDTO;
import com.diploma.ivan.service.IConfigurationService;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping(
        path = "user/v1/configuration"
)
public class ConfigurationController {

    private static final String NAME_VARIABLE = "name";
    private final IConfigurationService configurationService;

    public ConfigurationController(IConfigurationService configurationService) {
        this.configurationService = configurationService;
    }

    @GetMapping
    public ModelAndView getConfigurations(Model model) {
        List<MonitoringConfigurationDTO> configurationDTOList = configurationService.getAll();
        model.addAttribute("configurations", configurationDTOList);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("configurations");

        return modelAndView;
    }

    @PostMapping
    public ResponseEntity<MonitoringConfigurationDTO> create( //
            @RequestBody @Valid MonitoringConfigurationDTO configuration //
    ) {
        return status(CREATED).body(configurationService.create(configuration));
    }

    @PutMapping(path = "/{name}")
    public ResponseEntity<MonitoringConfigurationDTO> update( //
            @PathVariable(NAME_VARIABLE) String name, //
            @RequestBody @Valid MonitoringConfigurationDTO newConfiguration //
    ) {
        return status(OK).body(configurationService.update(name, newConfiguration));
    }

    @DeleteMapping(path = "/{name}")
    public ResponseEntity<Void> delete( //
            @PathVariable(NAME_VARIABLE) String name //
    ) {
        configurationService.delete(name);

        return new ResponseEntity<>(NO_CONTENT);
    }

}
