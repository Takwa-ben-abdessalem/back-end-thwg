package com.whitecape.auth.models.cart;

import java.io.Serializable;
import java.util.Objects;

import org.springframework.data.mongodb.core.mapping.DBRef;

import com.whitecape.auth.models.Event;
import com.whitecape.auth.models.User;

public class CartItemPK implements Serializable {
    @DBRef
    private User user;

    @DBRef
    private Event product;

  
    
    public CartItemPK(User user, Event product) {
        this.user = user;
        this.product = product;
    }
    public CartItemPK () {

    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Event getProduct() {
        return product;
    }

    public void setProduct(Event product) {
        this.product = product;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass())
            return false;

        CartItemPK that = (CartItemPK) o;
        return Objects.equals(user, that.user) &&
                Objects.equals(user, that.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, product);
    }
}