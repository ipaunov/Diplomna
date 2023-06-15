package com.diploma.ivan.service;

import com.diploma.ivan.model.dto.UserDTO;
import com.diploma.ivan.model.entity.User;

import java.util.List;

public interface IUserService {

    List<UserDTO> getAll();
    UserDTO getByName(String name);
    UserDTO create(UserDTO user);
    UserDTO update(String name, UserDTO user);
    void delete(String name);
}
