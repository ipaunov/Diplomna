package com.diploma.ivan.model.security;

import java.util.UUID;

public interface IUser {
    public void setId(UUID id);

    public UUID getId();

    public String getName();

    public void setName(String name);

    public String getPassword();

    public void setPassword(String password);

    public String getRole();

    public void setRole(String role);
}
