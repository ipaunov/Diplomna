package com.diploma.ivan.service;

import com.diploma.ivan.model.dto.UserDTO;
import com.diploma.ivan.model.entity.User;
import com.diploma.ivan.repository.UserRepository;
import com.diploma.ivan.util.Converter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;
import java.util.stream.Collectors;

import static com.diploma.ivan.util.Converter.convertToDto;
import static com.diploma.ivan.util.Converter.convertToEntity;

public class UserService implements IUserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<UserDTO> getAll() {
        return userRepository.findAll().stream().map(Converter::convertToDto).collect(Collectors.toList());
    }

    @Override
    public UserDTO getByName(String name) {
        //TODO custom exception
        return convertToDto(userRepository.findByName(name).orElseThrow(() -> new RuntimeException("Test")));
    }

    @Override
    public UserDTO create(UserDTO user) {
        User userEntity = convertToEntity(user);
        userEntity.setPassword(passwordEncoder.encode(user.getPassword()));
        return convertToDto(userRepository.saveAndFlush(userEntity));
    }

    @Override
    public UserDTO update(String name, UserDTO user) {
        //TODO custom exception
        User toBeUpdated = userRepository.findByName(name).orElseThrow(() -> new RuntimeException("Test"));
        User newUser = convertToEntity(user);
        newUser.setId(toBeUpdated.getId());

        return convertToDto(userRepository.saveAndFlush(newUser));
    }

    @Override
    public void delete(String name) {
        //TODO custom exception
        User toBeDeleted = userRepository.findByName(name).orElseThrow(() -> new RuntimeException("Test"));

        userRepository.delete(toBeDeleted);
    }
}
