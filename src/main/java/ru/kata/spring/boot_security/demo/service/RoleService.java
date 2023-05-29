package ru.kata.spring.boot_security.demo.service;

import org.springframework.data.jpa.repository.EntityGraph;
import ru.kata.spring.boot_security.demo.molel.Role;

import java.util.List;

public interface RoleService {

    @EntityGraph(attributePaths = {"users"})
    public List<Role> getAllRoles();
}
