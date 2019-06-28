package com.hackerrank.github.service;

import com.hackerrank.github.datautil.ErrorOperationResult;
import com.hackerrank.github.model.Event;
import com.hackerrank.github.datautil.OperationResult;
import com.hackerrank.github.datautil.SuccessfulOperationResult;
import com.hackerrank.github.repository.ActorRepository;
import com.hackerrank.github.repository.EventRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * author: acerbk
 * Date: 2019-06-28
 * Time: 19:46
 */
@Service
public class EventService {

    private final EventRepository eventRepository;
    private final ActorRepository actorRepository;

    public EventService(EventRepository eventRepository, ActorRepository actorRepository) {
        this.eventRepository = eventRepository;
        this.actorRepository = actorRepository;
    }

    public void eraseAllEvents() {
        eventRepository.deleteAll();
    }

    public OperationResult addNewEvent(Event event) {
        if (event.getId() != null) {
            if (!eventRepository.exists(event.getId())) {
                return saveEvent(event);
            }
            return new ErrorOperationResult(null, "event with id of " + event.getId() + " already exists", 400);
        }
        return saveEvent(event);
    }

    private OperationResult saveEvent(Event event) {
        Event eventSaved = eventRepository.save(event);
        if (Objects.nonNull(eventSaved)) {
            return new SuccessfulOperationResult(eventSaved, "Success", 201);
        }
        return new ErrorOperationResult(null, "Server Error Occurred.Kindly retry", 500);
    }

    /**
     * get all events and ordered by Id Ascending
     *
     * @return
     */
    public OperationResult getAllEventsOrderedByIdAsc() {
        List<Event> eventList = eventRepository.findAllByOrderByIdAsc();
        return new SuccessfulOperationResult(eventList, "Success", 200);
    }

    public OperationResult getAllEventsByActor(Long actorId) {
        if (actorRepository.exists(actorId)) {
            List<Event> eventList = eventRepository.findAllByActor_IdOrderByIdAsc(actorId);
            return new SuccessfulOperationResult(eventList, "Success", 200);
        }
        return new ErrorOperationResult(null, "Actor with id=" + actorId + " does not exist", 404);
    }
}
