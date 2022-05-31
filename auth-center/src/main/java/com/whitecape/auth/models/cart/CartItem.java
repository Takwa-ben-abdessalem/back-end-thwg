package com.whitecape.auth.models.cart;

import java.util.Date;
import java.util.Objects;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.whitecape.auth.models.Event;
import com.whitecape.auth.models.User;
@Document(collection = "cart_item")

public class CartItem {
	@Id
    private CartItemPK pk;

    private Date addedOn = new Date();


   

    public CartItem (User user, Event product) {
        pk = new CartItemPK();
        pk.setUser(user);
        pk.setProduct(product);
    }
    public CartItem () {

    }

    public Event getProduct () {
        return pk.getProduct();
    }
    public User getUser () {
        return pk.getUser();
    }

  

  

    public CartItemPK getPk() {
        return pk;
    }

    public void setPk(CartItemPK pk) {
        this.pk = pk;
    }

    public Date getAddedOn() {
        return addedOn;
    }

    public void setAddedOn(Date addedOn) {
        this.addedOn = addedOn;
    }

   
   

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass())
            return false;

        CartItem that = (CartItem) o;
        return Objects.equals(pk.getUser().getId(), that.pk.getUser().getId()) &&
                Objects.equals(getProduct().getId(), that.getProduct().getId());
    }
}