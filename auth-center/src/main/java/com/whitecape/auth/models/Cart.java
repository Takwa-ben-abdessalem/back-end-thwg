package com.whitecape.auth.models;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "cart")

public class Cart {
	    @Id
	    @NotNull
	    private String cartId;
	    
	    @DBRef

	    private User user;


        @DBRef	    
	    private Set<Event> products = new HashSet<>();

	    @Override
	    public String toString() {
	        return "Cart{" +
	                "cartId=" + cartId +
	                ", products=" + products +
	                '}';
	    }

	    public Cart(User user) {
	        this.user  = user;
	    }
}