package com.whitecape.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.whitecape.auth.models.Role;
import com.whitecape.auth.repository.UserRoleRepository;

@RestController
@RequestMapping("/roles")
public class RoleController {

    @Autowired
    private UserRoleRepository roleRepository;

    @PostMapping
    public Role createNewRole(@RequestBody Role role) {
        return roleRepository.save(role);
    }
}