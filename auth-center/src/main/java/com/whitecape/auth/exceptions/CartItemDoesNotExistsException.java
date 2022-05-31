package com.whitecape.auth.exceptions;


public class CartItemDoesNotExistsException extends RuntimeException {
    public CartItemDoesNotExistsException(String message) {
        super(message);
    }
}
