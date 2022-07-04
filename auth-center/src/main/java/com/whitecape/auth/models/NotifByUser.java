package com.whitecape.auth.models;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.Binary;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "notif")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class NotifByUser {

	
	public NotifByUser(String userId ) {
		super();
		this.userId = userId;
	}
	@Id
	private String id;
	public NotifByUser(String userId, List<chatMessageDto> messages) {
		super();
		this.userId = userId;
		this.messages = messages;
	}
    private String userId ;
    private  List<chatMessageDto> messages = new ArrayList<>();
    private int NotifCount =0 ;   
}

