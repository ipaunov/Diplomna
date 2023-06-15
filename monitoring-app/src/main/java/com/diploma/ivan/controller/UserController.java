package com.diploma.ivan.controller;

import com.diploma.ivan.model.dto.UserDTO;
import com.diploma.ivan.service.IUserService;
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
        value = "admin/v1/users"
)
public class UserController {

    private static final String NAME_VARIABLE = "name";
    
    private final IUserService userService;
    
    public UserController(IUserService userService) {
        this.userService = userService;
    }
    
    
    @GetMapping
    public ModelAndView getUsers(Model model) {
        List<UserDTO> users = userService.getAll();
        model.addAttribute("users", users);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin/users");

        return modelAndView;
    }

    @GetMapping(path = "/{name}")
    public ResponseEntity<UserDTO> getUserByName( //
            @PathVariable(NAME_VARIABLE) String name //
    ) {
        return status(OK).body(userService.getByName(name));
    }

    @PostMapping
    public ResponseEntity<UserDTO> create( //
            @RequestBody @Valid UserDTO user //
    ) {
        return status(CREATED).body(userService.create(user));
    }

    @PutMapping(path = "/{name}")
    public ResponseEntity<UserDTO> update( //
            @PathVariable(NAME_VARIABLE) String name, //
            @RequestBody @Valid UserDTO newUser //
    ) {
        return status(OK).body(userService.update(name, newUser));
    }

    @DeleteMapping(path = "/{name}")
    public ResponseEntity<Void> delete( //
            @PathVariable(NAME_VARIABLE) String name //
    ) {
        userService.delete(name);

        return new ResponseEntity<>(NO_CONTENT);
    }
    
}
