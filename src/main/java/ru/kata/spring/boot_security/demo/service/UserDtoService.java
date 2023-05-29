package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.dto.RoleDto;
import ru.kata.spring.boot_security.demo.dto.UserDto;
import ru.kata.spring.boot_security.demo.molel.Role;

import java.util.List;


public interface UserDtoService {

    public List<UserDto> getAllUsers();

    public List<RoleDto> getRolesDto(List<Role> roles);

    public UserDto getById(long id);
}
