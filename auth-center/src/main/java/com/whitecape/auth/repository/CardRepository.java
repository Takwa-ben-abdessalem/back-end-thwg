package com.whitecape.auth.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import paymentService.model.Card;



@Repository

public interface CardRepository extends MongoRepository<Card,String> {
	   void deleteById(String id);
	    Card findCardById(String id);
	    
	    @Query("{ 'cardNumber' : ?0 }")
	    Card findCardByCardNumber(String number);

}
