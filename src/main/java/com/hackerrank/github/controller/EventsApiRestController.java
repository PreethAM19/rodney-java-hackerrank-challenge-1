package com.hackerrank.github.controller;

import com.hackerrank.github.datautil.OperationResult;
import com.hackerrank.github.model.Event;
import com.hackerrank.github.service.EventService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * author: acerbk
 * Date: 2019-06-29
 * Time: 20:25
 */
@RestController
public class EventsApiRestController {

    private final EventService eventService;

    public EventsApiRestController(EventService eventService) {
        this.eventService = eventService;
    }

    /**
     * 1. Erasing all the events:
     * The service should be able to erase all the events by the DELETE request at /erase.
     * The HTTP response code should be 200.
     *
     * @return
     */
    @DeleteMapping("/erase")
    public ResponseEntity eraseAllEvents() {
        eventService.eraseAllEvents();

        return ResponseEntity.ok(null);
    }

    /**
     * 2.Adding new events:
     * The service should be able to add a new event by the POST request at /events.
     * The event JSON is sent in the request body.
     * If an event with the same id already exists then the HTTP response code should be 400,
     * otherwise, the response code should be 201.
     *
     * @param event
     * @return
     */
    @PostMapping("/events")
    public ResponseEntity<?> addNewEvent(@RequestBody Event event) {
        OperationResult operationResult = eventService.addNewEvent(event);
        Object data = operationResult.getData();
        return ResponseEntity.status(operationResult.getStatusCode())
                .body(null);
    }

    /**
     * 3.Returning all the events:
     * The service should be able to return the JSON array
     * of all the events by the GET request at /events.
     * The HTTP response code should be 200.
     * The JSON array should be sorted in ascending order by event ID.
     *
     * @return {@link ResponseEntity}
     */
    @GetMapping("/events")
    public ResponseEntity<?> getAllEvents() {
        OperationResult operationResult = eventService.getAllEventsOrderedByIdAscending();

        return ResponseEntity.status(operationResult.getStatusCode()).body(operationResult.getData());
    }

    /**
     * 4.Returning the event records filtered by the actor ID:
     * The service should be able to return the JSON array of all the events which
     * are performed by the actor ID by the GET request at /events/actors/{actorID}.
     * If the requested actor does not exist then HTTP response code should be 404,
     * otherwise, the response code should be 200.
     * The JSON array should be sorted in ascending order by event ID.
     *
     * @param actorID
     * @return
     */
    @GetMapping("/events/actors/{actorID}")
    public ResponseEntity<?> getAllEventsByActor(@PathVariable Long actorID) {
        OperationResult operationResult = eventService.getAllEventsByActorOrderByIdAscending(actorID);

        return ResponseEntity.status(operationResult.getStatusCode())
                .body(operationResult.getData());
    }
}
