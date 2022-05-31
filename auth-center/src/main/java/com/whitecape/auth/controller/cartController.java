package com.whitecape.auth.controller;

/*

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import com.whitecape.auth.models.Event;
import com.whitecape.auth.models.User;
import com.whitecape.auth.models.cart.CartItem;
import com.whitecape.auth.models.cart.CartItemPK;
import com.whitecape.auth.repository.EventRepository;
import com.whitecape.auth.service.CartItemService;
import com.whitecape.auth.service.EventService;
import com.whitecape.auth.service.UserService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class cartController {
	@Autowired
     UserService userService;
	@Autowired

     EventService productService;
	@Autowired

     CartItemService cartItemService;
	@Autowired
	private EventRepository eventRepository;
  

  
   

    @GetMapping("/users/{id}/cart")
    public ResponseEntity<List<CartItem>> getUserCart (@PathVariable("id") String id) {
        System.out.println(userService.getUser(id).getCartItems().size());
        return new ResponseEntity<>(userService.getUser(id).getCartItems(), HttpStatus.OK);
    }

    @PostMapping("/users/{id}/cart/add/{productId}")
    public ResponseEntity<User> addToUserCart (@PathVariable("id") String id,
                                               @PathVariable("productId") String productId) {
        User user = userService.getUser(id);
        Event product = productService.getProduct(productId);

        CartItem cartItem = new CartItem(user, product);
        cartItemService.addCartItem(cartItem);

        return new ResponseEntity<>(userService.getUser(id), HttpStatus.CREATED);
    }
    @PostMapping("/users/{id}/cart/add")
    public ResponseEntity<User> addToUserCart (@PathVariable("id") String id, @RequestBody Event event) {
        User user = userService.getUser(id);
		Event insertedEvent = eventRepository.insert(event);

        CartItem cartItem = new CartItem(user, insertedEvent);
        cartItemService.addCartItem(cartItem);

        return new ResponseEntity<>(userService.getUser(id), HttpStatus.CREATED);
    }

    @PutMapping("/users/{id}/cart/update/{productId}")
    public ResponseEntity<User> updateCartItem (@PathVariable("id") String id,
                                                @PathVariable("productId") String productId,
                                                @RequestBody CartItem cartItem) {
        User user = userService.getUser(id);
        Event product = productService.getProduct(productId);

        cartItem.setPk(new CartItemPK(user, product));
        cartItemService.updateCartItem(cartItem);

        return new ResponseEntity<>(userService.getUser(id), HttpStatus.OK);
    }

    @DeleteMapping("/users/{id}/cart/remove/{productId}")
    public ResponseEntity<User> removeFromUserCart (@PathVariable("id") String id,
                                                    @PathVariable("productId") String productId) {
        cartItemService.deleteCartItem(id,productId);

        return new ResponseEntity<>(userService.getUser(id), HttpStatus.OK);
    }

   

    @GetMapping("users/cart-items")
    public ResponseEntity<List<CartItem>> getCartItems () {
        return ResponseEntity.ok(cartItemService.getCartItems());
    }

    @CrossOrigin
    @GetMapping("/users/cart-items/{id}/{productId}")
    public ResponseEntity<CartItem> getCartItem (@PathVariable("id") String id,
                                                 @PathVariable("productId") String productId) {
        return ResponseEntity.ok(cartItemService.getCartItem(id, productId));
    }
}*/