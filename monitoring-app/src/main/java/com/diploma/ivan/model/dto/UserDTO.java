package com.diploma.ivan.model.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class UserDTO {

    private final String name;

    private final String password;
    private final String role;


    @JsonCreator
    public UserDTO( //
            @JsonProperty("name") String name, //
            @JsonProperty("password") String password, //
            @JsonProperty("role") String role //
    ) {
        this.name = name;
        this.password = password;
        this.role = role;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("password")
    public String getPassword() {
        return password;
    }

    @JsonProperty("role")
    public String getRole() {
        return role;
    }
}
