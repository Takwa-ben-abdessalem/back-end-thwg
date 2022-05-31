package com.whitecape.auth.service;

import java.util.List;
import java.util.Optional;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.stereotype.Service;

import com.whitecape.auth.exceptions.CartItemAlreadyExistsException;
import com.whitecape.auth.models.Event;
import com.whitecape.auth.models.User;
import com.whitecape.auth.models.cart.CartItem;
import com.whitecape.auth.repository.EventRepository;
import com.whitecape.auth.repository.UserRepository;
@Validated
@Service
public class EventService {

    
	@Autowired
	EventRepository repo;
    
  
    
    public List<Event> getProducts () {
        return repo.findAll();
    }

    public Event getProduct (String id) {
        return repo.findEventById(id);}
    
    
    
    
   
   
    public Event addProduct (Event product) {
        return repo.save(product);
    }



    public void deleteProduct (String id) {
        repo.deleteById(id);
    }
}