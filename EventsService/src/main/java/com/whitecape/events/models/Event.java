package com.whitecape.events.models;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "mydb")
@NoArgsConstructor
@Data
@AllArgsConstructor

public class Event {
	@Id
    private String id;

    @NotNull(message = "event name is required.")
    private String title;

    private String price;

    private String image;
    private String type;
    private String link;

    public Event( String title, String price , String image , String type , String link ) {
        super();
        this.title = title;
        this.price = price;
        this.image = image;
        this.type = type;
        this.link = link;


    }

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

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
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

    // all arguments contructor 
    // standard getters and setters

}
