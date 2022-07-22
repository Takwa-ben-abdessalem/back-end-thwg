package com.whitecape.auth.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.whitecape.auth.models.Event;
import com.whitecape.auth.models.EventsByUser;
import com.whitecape.auth.models.cart.CartItem;

import paymentService.model.Card;
@Repository

public interface EventsByUserRepository extends MongoRepository<EventsByUser,String> {
	   void deleteById(String id);
	    EventsByUser findEventById(String id);
	  
	    @Query("{ 'event' : ?0 }")
	    EventsByUser findEventsByUserByEvent(Event event);
	  
}
