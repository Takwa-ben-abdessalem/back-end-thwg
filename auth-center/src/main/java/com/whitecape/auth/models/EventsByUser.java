package com.whitecape.auth.models;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.constraints.NotNull;

import org.bson.types.Binary;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Document(collection = "eventsByUser")
@NoArgsConstructor
@Data
@AllArgsConstructor

public class EventsByUser {

    @Id
    private String id ;
	@DBRef
    private Event event;
        
    @DBRef
    private List<User> participant = new ArrayList<>();
    //Map<String,List<User>> participant = new HashMap<String,List<User>>();
    

 
}
