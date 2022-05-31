package com.whitecape.auth.repository;


import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.whitecape.auth.models.User;
@Repository
public interface UserRepository extends MongoRepository<User, String> {
	  Optional<User> findByUsername(String username);
	      User findUserById(String id);
		  Boolean existsByUsername(String username);

		  Boolean existsByEmail(String email);
		}