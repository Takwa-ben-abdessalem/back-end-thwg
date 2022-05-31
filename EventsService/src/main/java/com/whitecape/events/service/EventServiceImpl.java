package com.whitecape.events.service;

import java.util.stream.Collectors;
import java.util.Collection;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.whitecape.events.exception.ResourceNotFoundException;
import com.whitecape.events.models.Event;
import com.whitecape.events.repository.EventRepository;

@Service
@Transactional
public class EventServiceImpl implements EventService {
    @Resource

    private EventRepository eventRepository;
    public EventServiceImpl(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public Iterable<Event> getAllEvents() {
        //return eventRepository.findAll().stream().map(s -> mapper.map(s, EventBean.class)).collect(Collectors.toList());
        return eventRepository.findAll();

    }

    @Override
    public Event getEvent(Event product) {
        Event productDto = new Event();
        productDto.setTitle(product.getTitle());
        productDto.setPrice(product.getPrice());
        productDto.setImage(product.getImage());
        productDto.setType(product.getType());
        productDto.setLink(product.getLink());
        return productDto;
    }
    
    @Override
    public Event getEventId(String id) {
        return eventRepository
        		.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Event not found"));
    }
     
 
    @Override
    public Event updateEvent(Event event) {
        return eventRepository.save(event);
    }

    @Override
    public Event save(Event event) {
        return eventRepository.save(event);
    }
}