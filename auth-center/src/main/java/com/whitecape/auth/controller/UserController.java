package com.whitecape.auth.controller;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import org.apache.kafka.common.errors.ResourceNotFoundException;
import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.HttpStatus;

import com.whitecape.auth.models.Event;
import com.whitecape.auth.models.User;
import com.whitecape.auth.repository.EventRepository;
import com.whitecape.auth.repository.UserRepository;
import com.whitecape.auth.service.EventService;
import com.whitecape.auth.service.UserService;



@RestController
@CrossOrigin
@RequestMapping("/api")
public class UserController {
	private EventService eventService;
	@Autowired
	private EventRepository eventRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
    UserService userService;
	@GetMapping("/users")
    public ResponseEntity<List<User>> getUsers () {
        return new ResponseEntity<>(userService.getUsers(), HttpStatus.OK);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUser (@PathVariable("id") String id) {
        return new ResponseEntity<>(userService.getUser(id), HttpStatus.OK);
    }
 
    @GetMapping("/users/eventsParticipated/{id}")
    public ResponseEntity<List<Event>> geteventsParticipated (@PathVariable("id") String id) {
        return new ResponseEntity<>(userService.getUser(id).getEventPartcipated(), HttpStatus.OK);
    }
    @GetMapping("/users/eventsAdded/{id}")
    public ResponseEntity<List<Event>> geteventsAdded (@PathVariable("id") String id) {
        return new ResponseEntity<>(userService.getUser(id).getEventAdded(), HttpStatus.OK);
    }
    
	@PostMapping(value = "/users/image/{id}")

	public ResponseEntity<User> AddImage(@PathVariable(value = "id") String userId,@RequestParam("myFile") MultipartFile file
			)  throws IOException {
		
		if (file == null ) {
			throw new RuntimeException("You must select at least one file for uploading");
		}

       
			Binary pics =(new Binary(BsonBinarySubType.BINARY, file.getBytes()));		

		
			    User user = userRepository.findUserById(userId);
			    		

				user.setPicture(pics);
				userRepository.save(user);

			




			  
			    return ResponseEntity.ok(user);
			}
	
    
	

}
  

