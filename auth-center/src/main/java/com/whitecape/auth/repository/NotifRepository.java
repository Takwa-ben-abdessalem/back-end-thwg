package com.whitecape.auth.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.whitecape.auth.models.Event;
import com.whitecape.auth.models.NotifByUser;
import com.whitecape.auth.models.chatRoomByEvent;

import paymentService.model.Card;



@Repository

public interface NotifRepository extends MongoRepository<NotifByUser, String> {

	
    @Query("{ 'userId' : ?0 }")
    NotifByUser findNotifByUserId(String userId);
	}