package com.whitecape.auth.controller;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.apache.kafka.common.errors.ResourceNotFoundException;
import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.whitecape.auth.exceptions.CartItemAlreadyExistsException;
import com.whitecape.auth.models.Event;
import com.whitecape.auth.models.Place;
import com.whitecape.auth.repository.CardRepository;
import com.whitecape.auth.repository.EventRepository;
import com.whitecape.auth.repository.UserRepository;
import com.whitecape.auth.repository.chatRepository;
import com.whitecape.auth.service.EventService;
import com.whitecape.auth.service.UserService;

import paymentService.model.Card;

import com.whitecape.auth.models.User;
import com.whitecape.auth.models.chatMessageDto;
import com.whitecape.auth.models.chatRoomByEvent;






@RestController
@RequestMapping("/api/events")
@CrossOrigin("*")
public class EventController {

	@Autowired
	private EventRepository eventRepository;
	@Autowired
    UserService userService;
	
	@Autowired(required = false)


	private CardRepository cardRepository;
	
	@Autowired
	private UserRepository userRepository;
	@Autowired  ServletContext context;
	
	@Autowired
	chatRepository chatRepository ;
	/**
	 * Gets all events.
	 *
	 * @return all events
	 */
	private EventService eventService;
	

	@GetMapping(value = "/all")
    @ResponseStatus(HttpStatus.OK)

    public @NotNull Iterable<Event> getEvents() {
		 return eventRepository.findAll();
    }
	
	

	@GetMapping(value = "/image/{id}")
    @ResponseStatus(HttpStatus.OK)

    public  List<Binary> getEventImage(@PathVariable("id") String id) {
		 return eventRepository.findEventById(id).getPics();
    }
	

	/**
	 * Gets event.
	 *
	 * @return event if exists
	 *
	@GetMapping(value = "/all/{id}")
	public Optional<Event> get(@PathVariable String id) {
		return eventRepository.findById(id);
	}*/
	
	@GetMapping(value = "/all/{id}")
    @ResponseStatus(HttpStatus.OK)

    public Event getEvent (@PathVariable String id) {
        return eventRepository.findEventById(id);
    }
	

    @PostMapping(value ="/all/{id}/add/{productId}")
    public ResponseEntity<Event> addParticipant (@PathVariable("id") String id,
            @PathVariable("productId") String productId) {

    	Event product = eventRepository.findEventById(productId);

        product.getParticipant().add(userService.getUser(id));
        


        
        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }
	
	@GetMapping(value = "/all/organizer/{id}")
    @ResponseStatus(HttpStatus.OK)

    public User getOrganizer (@PathVariable String id) {
        return eventRepository.findEventById(id).getOrganizer();
    }
	
	
	@GetMapping(value = "/participant/{id}")
    @ResponseStatus(HttpStatus.OK)

    public List<User> getPartcipants (@PathVariable String id) {
        return eventRepository.findEventById(id).getParticipant();
    }
	
	
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")

	@PostMapping(value = "/add")
	public String persist(@RequestBody Event event) {
	
		Event insertedEvent = eventRepository.insert(event);
	

		return insertedEvent.getTitle() + " created successfully!\"" ;
		


	 	
	}
	  @PostMapping("/add/{id}")
	    public ResponseEntity<Event> addEventWithOrganizer ( @PathVariable("id") String id, @RequestBody Event event) throws IOException{
	    	
	    	
	    	

	    	
	    	event.setOrganizer(userService.getUser(id));

			Event insertedEvent = eventRepository.insert(event);
			insertedEvent.getOrganizer().getEventAdded().add(insertedEvent);
			insertedEvent.getOrganizer().setEventAdded(insertedEvent.getOrganizer().getEventAdded());
			userRepository.save(insertedEvent.getOrganizer());
			Event insertedEvent2 = eventRepository.save(insertedEvent);

		

	        return new ResponseEntity<>(insertedEvent2, HttpStatus.CREATED);
	    }
    /*
    @PostMapping("/upload")
    public ResponseEntity<Event> addImage ( @RequestParam("event") Event event,@RequestParam("myFile") MultipartFile[] file) throws IOException{
    	
    	
    	

		if (file == null || file.length == 0) {
			throw new RuntimeException("You must select at least one file for uploading");
		}

                /*** Multiple files ***
		StringBuilder sb = new StringBuilder(file.length);
		List<Binary> pics = new ArrayList<>();
		for (int i = 0; i < file.length; i++) {
			pics.add(new Binary(BsonBinarySubType.BINARY, file[i].getBytes()));
		}

		event.setPics(pics);
		event = eventRepository.insert(event);

        return new ResponseEntity<>(event, HttpStatus.CREATED);
    } */



	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")

	
	@DeleteMapping(value = "/delete/{id}")
	public String delete(@PathVariable String id) {
		eventRepository.deleteById(id);
		return "Event deleted with id : " +id;
	}
	
	@PostMapping(value = "/all/image/{productId}")

	public ResponseEntity<Event> AddImage(@PathVariable(value = "productId") String eventId,@RequestParam("myFile") MultipartFile[] file
			)  throws IOException {
		
		if (file == null || file.length == 0) {
			throw new RuntimeException("You must select at least one file for uploading");
		}

                /*** Multiple files ***/
		StringBuilder sb = new StringBuilder(file.length);
		List<Binary> pics = new ArrayList<>();
		for (int i = 0; i < file.length; i++) {
			pics.add(new Binary(BsonBinarySubType.BINARY, file[i].getBytes()));		}

		
			    Event event = eventRepository.findById(eventId)
			    		
			    .orElseThrow(() -> new ResourceNotFoundException("Event not found for this id :: " + eventId));

				event.setPics(pics);
				eventRepository.save(event);

			




			  
			    return ResponseEntity.ok(event);
			}
	
	@PostMapping(value = "/all/place/{productId}")

	public ResponseEntity<Event> AddPlace(@PathVariable(value = "productId") String eventId,@RequestBody Place place)
			 {
		
		

		
			    Event event = eventRepository.findById(eventId)
			    		
			    .orElseThrow(() -> new ResourceNotFoundException("Event not found for this id :: " + eventId));

				event.setPlace(place);
				eventRepository.save(event);

			




			  
			    return ResponseEntity.ok(event);
			}

	

	@PostMapping(value = "/all/{id}/{productId}")

	public ResponseEntity<Event> AddParticipant(@PathVariable(value = "productId") String eventId,@PathVariable(value = "id") String userId
			  ) throws ResourceNotFoundException {
		
		
			    Event event = eventRepository.findById(eventId)
			    		
			    .orElseThrow(() -> new ResourceNotFoundException("Event not found for this id :: " + eventId));

		
			    event.getParticipant().add(userService.getUser(userId));

			    event.setParticipant(event.getParticipant());
			    eventRepository.save(event);

			    event.getParticipant().get(event.getParticipant().size() - 1).getEventPartcipated().add(event);
			    event.getParticipant().get(event.getParticipant().size() - 1).setEventPartcipated(event.getParticipant().get(event.getParticipant().size() - 1).getEventPartcipated());
				userRepository.save( event.getParticipant().get(event.getParticipant().size() - 1));
				Event eventParticipated = eventRepository.save(event);




			  
			    return ResponseEntity.ok(eventParticipated);
			}
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")

	@PutMapping(value = "/put/{id}")

	public ResponseEntity<Event> updateEvent(@PathVariable(value = "id") String eventId,
			  @Valid @RequestBody Event eventDetails) throws ResourceNotFoundException {
			    Event event = eventRepository.findById(eventId)
			    .orElseThrow(() -> new ResourceNotFoundException("Event not found for this id :: " + eventId));
			    event.setTitle(eventDetails.getTitle()); 
			    event.setPrice(eventDetails.getPrice());
			    event.setType(eventDetails.getType());
			    event.setLink(eventDetails.getLink());
			    
			    event.setParticipant(eventDetails.getParticipant());


			  
			    final Event updatedEvent = eventRepository.save(event);
			    return ResponseEntity.ok(updatedEvent);
			}
	@PatchMapping("/patch/{id}/{title}")
	public ResponseEntity<Event> updateEventPartially(@PathVariable String id, @PathVariable String title) {
		try {
			Event event = eventRepository.findById(id).get();
			event.setTitle(title);
			return new ResponseEntity<Event>(eventRepository.save(event), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	

@PostMapping("/add/{number}/{productId}")
public ResponseEntity<Event> addCard (@PathVariable(value = "number") String number , @PathVariable(value = "productId") String eventId) 
	
	
	

	
	throws ResourceNotFoundException {
	
    Card card = cardRepository.findCardByCardNumber(number);

		
		
	    Event event = eventRepository.findById(eventId)
	    		
	    .orElseThrow(() -> new ResourceNotFoundException("Event not found for this id :: " + eventId));
	  
	   

     event.setEventCard(card);   
	Event eventWithCard = eventRepository.save(event);



    return new ResponseEntity<>(eventWithCard, HttpStatus.CREATED);
}

@PostMapping(value = "add/card/amount/{productId}/{amount}")
@ResponseStatus(HttpStatus.OK)

public  Card increaseCardAmount(@PathVariable ("amount") double amount,@PathVariable(value = "productId") String eventId ) throws ResourceNotFoundException{
	
	
    Event event = eventRepository.findById(eventId)
    		
    .orElseThrow(() -> new ResourceNotFoundException("Event not found for this id :: " + eventId));
	
	double  amount2 = event.getEventCard().getAmount() ;
	event.getEventCard().setAmount(amount2 + amount)  ;
	cardRepository.save(event.getEventCard());
	return  event.getEventCard();


	
}

@PostMapping(value = "/add/chat/{eventId}/{userId}")
@ResponseStatus(HttpStatus.OK)

public  chatRoomByEvent addChat(@PathVariable("eventId") String eventId , @PathVariable("userId") String userId, @RequestBody String message){
	
	   Event event = eventRepository.findById(eventId)
	    		
			    .orElseThrow(() -> new ResourceNotFoundException("Event not found for this id :: " + eventId));	 
	   
	   for (chatRoomByEvent item : chatRepository.findAll()) {  
       	if (item.getEventId().equals(event.getId())) {
          chatRoomByEvent chatRoom = chatRepository.findChatByEventId(event.getId());
          chatMessageDto chat = new chatMessageDto(userService.getUser(userId),message,LocalDateTime.now(ZoneId.of("GMT+01:00")));

          
          chatRoom.getMessages().add(chat);
          chatRepository.save(chatRoom);
          return chatRoom;


       }
	   }       		
       chatRoomByEvent chatRoom = new chatRoomByEvent(event.getId());
     
      chatMessageDto chat = new chatMessageDto(userService.getUser(userId),message,LocalDateTime.now(ZoneId.of("GMT+01:00")));

       
       chatRoom.getMessages().add(chat);
       chatRepository.save(chatRoom);
       return chatRoom;



	
}

@GetMapping(value = "/get/chat/all")
@ResponseStatus(HttpStatus.OK)

public List<chatRoomByEvent> getAllChats () {
	
	 
   return chatRepository.findAll();
    
    
    
}

@GetMapping(value = "/get/chat/{id}")
@ResponseStatus(HttpStatus.OK)

public List<chatMessageDto> getchatByEvent (@PathVariable String id) {
	 Event event = eventRepository.findById(id)
	    		
			    .orElseThrow(() -> new ResourceNotFoundException("Event not found for this id :: " + id));	
	 
   return chatRepository.findChatByEventId(event.getId()).getMessages();
    
    
    
}




}
	
	
