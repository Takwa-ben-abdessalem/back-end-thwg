package com.whitecape.auth.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.whitecape.auth.models.User;

public interface ImageRepository extends MongoRepository<User, String>{

}
