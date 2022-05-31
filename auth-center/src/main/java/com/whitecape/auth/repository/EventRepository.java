package com.whitecape.auth.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.whitecape.auth.models.Event;
import com.whitecape.auth.models.cart.CartItem;
@Repository

public interface EventRepository extends MongoRepository<Event,String> {
	   void deleteById(String id);
	    Event findEventById(String id);
}
