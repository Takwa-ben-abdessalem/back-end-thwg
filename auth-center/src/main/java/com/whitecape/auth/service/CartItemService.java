package com.whitecape.auth.service;

import java.util.List;

import com.whitecape.auth.exceptions.CartItemAlreadyExistsException;
import com.whitecape.auth.exceptions.CartItemDoesNotExistsException;
import com.whitecape.auth.models.cart.CartItem;
import com.whitecape.auth.repository.CartItemRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartItemService {
	@Autowired
    private CartItemRepository repo;

    public CartItemService (CartItemRepository repo) {
        this.repo = repo;
    }

    public List<CartItem> getCartItems () {
        return repo.findAll();
    }

    public CartItem getCartItem (String userId, String productId) {
        for (CartItem item : getCartItems()) {
            if (item.getPk().getUser().getId() == userId && item.getPk().getProduct().getId() == productId) {
                return item;
            }
        }

        throw new CartItemDoesNotExistsException(
                "Cart item w/ user id " + userId + " and product id " + productId + " does not exist."
        );
    }

    public CartItem addCartItem(CartItem cartItem) {
        for (CartItem item : getCartItems()) {
            if (item.equals(cartItem)) {
                throw new CartItemAlreadyExistsException(
                        "Cart item w/ user id " + cartItem.getPk().getUser().getId() + " and product id " +
                        cartItem.getProduct().getId() + " already exists."
                );
            }
        }

        return this.repo.save(cartItem);
    }

    public CartItem updateCartItem(CartItem cartItem) {
        for (CartItem item : getCartItems()) {
            if (item.equals(cartItem)) {
                return repo.save(item);
            }
        }

        throw new CartItemDoesNotExistsException(
                "Cart item w/ user id " + cartItem.getPk().getUser().getId() + " and product id " +
                        cartItem.getProduct().getId() + " does not exist."
        );
    }

    public void deleteCartItem (String userId, String productId) {
                repo.delete(getCartItem(userId, productId));
                return;
    }
        

       
    
}