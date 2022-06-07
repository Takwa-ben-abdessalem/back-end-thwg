package com.whitecape.auth.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.bson.types.Binary;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import paymentService.model.Card;

@Document(collection = "events")
@NoArgsConstructor
@Data
@AllArgsConstructor

public class Event {


	@Id
    private String id;

    @NotNull(message = "event title is required.")
    private String title;
    
    
    
    @DBRef
    private User organizer ;   
    
    private String price;  //from scrapy
    private String prices; 

    private String description;
    private List<Binary> pics    ;
	  private Date cree;
	  private String type;
    private String link;
    private String image;
    
    private Place Place;
    
    @DBRef
    private Card EventCard;

    
    @DBRef
    private List<User> participant = new ArrayList<>();
  
 
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}


}
