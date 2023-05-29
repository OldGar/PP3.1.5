package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.dto.RoleDto;
import ru.kata.spring.boot_security.demo.dto.UserDto;
import ru.kata.spring.boot_security.demo.molel.Role;
import ru.kata.spring.boot_security.demo.molel.User;
import ru.kata.spring.boot_security.demo.repositories.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserDtoServiceImpl implements UserDtoService {

    private final UserRepository userRepository;

    @Autowired
    public UserDtoServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserDto> getAllUsers() {
        List<UserDto> usersDto = new ArrayList<>();
        List<User> users = userRepository.findAll();
        for(User user : users) {
            usersDto.add(new UserDto(user, getRolesDto(user.getRoles())));
        }

        return usersDto;
    }

    public List<RoleDto> getRolesDto(List<Role> roles) {
        return roles.stream()
                .map(RoleDto::new)
                .collect(Collectors.toList());
    }

    public UserDto getById(long id) {
        User user = userRepository.getById(id);
        UserDto userDto = new UserDto(user, getRolesDto(user.getRoles()));
        return userDto;
    }
}
