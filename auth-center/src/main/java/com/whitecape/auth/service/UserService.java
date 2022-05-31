package com.whitecape.auth.service;


import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.whitecape.auth.models.Role;
import com.whitecape.auth.models.User;
import com.whitecape.auth.repository.UserRepository;
import com.whitecape.auth.repository.UserRoleRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
@Service
@RequiredArgsConstructor
public class UserService {

	@Autowired
     UserRepository userRepository ;
	@Autowired

     PasswordEncoder passwordEncoder ;
	@Autowired

     UserRoleRepository roleRepository ;

    public User createNewUser(User user) {
        Role role = roleRepository.findById("USER").get();
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        user.setRoles(roles);
        user.setPassword(getEncodedPassword(user.getPassword()));
        return userRepository.save(user);
    }

    private String getEncodedPassword(String password) {
        return passwordEncoder.encode(password);
    }
    public List<User> getUsers () {
        return userRepository.findAll();
    }

    public User getUser (String id) {
        return userRepository.findUserById(id);}
}