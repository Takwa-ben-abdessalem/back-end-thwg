package com.whitecape.auth.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.whitecape.auth.models.ERole;
import com.whitecape.auth.models.Role;

@Repository
public interface UserRoleRepository extends MongoRepository<Role, String> {
		  Optional<Role> findByName(ERole name);
		}
