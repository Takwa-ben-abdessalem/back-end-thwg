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

@Document(collection = "chat")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class chatRoomByEvent {

	
	public chatRoomByEvent(String eventId ) {
		super();
		this.eventId = eventId;
	}
	@Id
	private String id;
	public chatRoomByEvent(String eventId, List<chatMessageDto> messages) {
		super();
		this.eventId = eventId;
		this.messages = messages;
	}
    private String eventId ;
    private  List<chatMessageDto> messages = new ArrayList<>();}

