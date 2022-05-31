package com.whitecape.events.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
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

import com.whitecape.events.exception.ResourceNotFoundException;
import com.whitecape.events.models.Event;
import com.whitecape.events.repository.EventRepository;
import com.whitecape.events.service.EventService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
@RestController
@RequestMapping("/api/events")
@CrossOrigin("*")
public class EventController {

	@Autowired
	private EventRepository eventRepository;

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
	/**
	 * Gets event.
	 *
	 * @return event if exists
	 */
	@GetMapping(value = "/all/{id}")
	public Optional<Event> get(@PathVariable String id) {
		return eventRepository.findById(id);
	}
	/*    private String title;

    private String price;

    private String image;
    private String type;
    private String link;*/
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")

	@PostMapping(value = "/add")
	public String persist(@RequestBody Event event) {
		/*Event event = new Event(EventRequest.getTitle(), 
				 EventRequest.getPrice(),EventRequest.getImage(),
				 EventRequest.getType(),EventRequest.getLink());*/
		Event insertedEvent = eventRepository.insert(event);
		return insertedEvent.getTitle() + " created successfully!\"" ;
		


	 	
	}
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")

	
	@DeleteMapping(value = "/delete/{id}")
	public String delete(@PathVariable String id) {
		eventRepository.deleteById(id);
		return "Event deleted with id : " +id;
	}
	
	/* public List<Event> put(@PathVariable String id, @RequestBody Event event) {
		if (eventRepository.existsById(id)) {
			eventRepository.save(event);
			
		}
		
		return eventRepository.findAll();
	} */
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")

	@PutMapping(value = "/put/{id}")

	public ResponseEntity<Event> updateEvent(@PathVariable(value = "id") String eventId,
			  @Valid @RequestBody Event eventDetails) throws ResourceNotFoundException {
			    Event event = eventRepository.findById(eventId)
			    .orElseThrow(() -> new ResourceNotFoundException("Event not found for this id :: " + eventId));
			    event.setTitle(eventDetails.getTitle()); 
			    event.setPrice(eventDetails.getPrice());
			    event.setImage(eventDetails.getImage());
			    event.setType(eventDetails.getType());
			    event.setLink(eventDetails.getLink());

			  
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
  
	
} 

