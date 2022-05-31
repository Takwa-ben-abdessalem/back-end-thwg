package com.whitecape.auth.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "event_images")


public class ImageModel {	
	
	
	public ImageModel() {
	super();
}	
	public ImageModel(String link, String type, byte[] picByte) {
	this.link = link;
	this.type = type;
	this.picByte = picByte;
}	
	@Id
   private Long id;	
   private String link;	
   private String type;    //image bytes can have large lengths so we specify a value
//which is more than the default length for picByte column
   private byte[] picByte;
 
}
