package com.whitecape.events.service;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.whitecape.events.models.Event;

@Validated
public interface EventService {

    @NotNull Iterable<Event> getAllEvents();

    Event getEventId(@Min(value = 1L, message = "Invalid event ID.") String id);

    Event save(Event event);
    Event updateEvent(Event user);
    Event getEvent(Event event);

}