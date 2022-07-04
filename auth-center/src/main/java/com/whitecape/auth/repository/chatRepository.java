package com.whitecape.auth.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.whitecape.auth.models.Event;
import com.whitecape.auth.models.chatRoomByEvent;

import paymentService.model.Card;



@Repository

public interface chatRepository extends MongoRepository<chatRoomByEvent, String> {

	
    @Query("{ 'eventId' : ?0 }")
    chatRoomByEvent findChatByEventId(String eventId);
	}