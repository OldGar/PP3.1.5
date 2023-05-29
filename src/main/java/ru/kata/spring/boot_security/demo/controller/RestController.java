package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.kata.spring.boot_security.demo.dto.RoleDto;
import ru.kata.spring.boot_security.demo.dto.UserDto;
import ru.kata.spring.boot_security.demo.molel.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserDtoService;
import ru.kata.spring.boot_security.demo.service.UserDtoServiceImpl;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.security.Principal;
import java.util.List;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/api")
public class RestController {

    private final RoleService roleService;
    private final UserService userService;
    private final UserDtoService userDtoService;

    @Autowired
    public RestController(RoleService roleService, UserService userService, UserDtoServiceImpl userDtoService) {
        this.roleService = roleService;
        this.userService = userService;
        this.userDtoService = userDtoService;
    }

    @GetMapping("/user/principal")
    public UserDto getUser(Principal principal) {
        Long id = userService.findByUsername(principal.getName()).getId();
        User user = userService.getById(id);
        List<RoleDto> rolesDto = userDtoService.getRolesDto(user.getRoles());
        return new UserDto(user, rolesDto);
    }

    @GetMapping("/admin/users")
    public List<UserDto> showUsers() {
        return userDtoService.getAllUsers();
    }

    @GetMapping("/admin/user/{id}")
    public UserDto getUserById(@PathVariable("id") Long id) {
        return userDtoService.getById(id);
    }

    @PostMapping("/admin/user")
    public UserDto addNewUser(@RequestBody User user) {
        userService.save(user);
        List<RoleDto> rolesDto = userDtoService.getRolesDto(user.getRoles());
        return new UserDto(user, rolesDto);
    }

    @PutMapping("/admin/user/{id}")
    public UserDto editUser(@RequestBody User user, @PathVariable int id) {
        userService.update(user, id);
        List<RoleDto> rolesDto = userDtoService.getRolesDto(user.getRoles());
        return new UserDto(user, rolesDto);
    }

    @DeleteMapping("/admin/user/{id}")
    public String deleteUser(@PathVariable long id) {
        userService.deleteById(id);
        return "User " + id + " delete!";
    }

    @GetMapping(value ="/admin/roles")
    public List<RoleDto> getRoles() {
        return userDtoService.getRolesDto(roleService.getAllRoles());
    }

    @GetMapping("/admin")
    public ModelAndView adminPage() {
        return new ModelAndView("admin");
    }

    @GetMapping("/user/page")
    public ModelAndView userPage() {
        return new ModelAndView("user");
    }
}
