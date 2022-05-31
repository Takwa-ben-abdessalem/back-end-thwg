package com.whitecape.auth.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.whitecape.auth.models.Event;
import com.whitecape.auth.models.User;
import com.whitecape.auth.models.cart.CartItem;
import com.whitecape.auth.models.cart.CartItemPK;
@Repository

public interface CartItemRepository extends MongoRepository<CartItem, CartItemPK> {

	}
