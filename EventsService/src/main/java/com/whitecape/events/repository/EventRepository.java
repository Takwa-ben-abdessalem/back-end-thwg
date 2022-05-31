package com.whitecape.events.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.whitecape.events.models.Event;

public interface EventRepository extends MongoRepository<Event,String> {
	   void deleteById(String id);
	    Optional<Event> findById (String id);
}
