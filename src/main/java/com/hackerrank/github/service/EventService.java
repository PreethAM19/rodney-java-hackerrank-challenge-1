package com.hackerrank.github.service;

import com.hackerrank.github.datautil.ErrorOperationResult;
import com.hackerrank.github.model.Actor;
import com.hackerrank.github.model.Event;
import com.hackerrank.github.datautil.OperationResult;
import com.hackerrank.github.datautil.SuccessfulOperationResult;
import com.hackerrank.github.model.Repo;
import com.hackerrank.github.repository.ActorRepository;
import com.hackerrank.github.repository.EventRepository;
import com.hackerrank.github.repository.RepoRepository;
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
    private final RepoRepository repoRepository;

    public EventService(EventRepository eventRepository, ActorRepository actorRepository, RepoRepository repoRepository) {
        this.eventRepository = eventRepository;
        this.actorRepository = actorRepository;
        this.repoRepository = repoRepository;
    }

    public void eraseAllEvents() {
        eventRepository.deleteAll();
    }

    public OperationResult addNewEvent(Event event) {

        if (Objects.nonNull(event.getId())) {
            if (!eventRepository.exists(event.getId())) {
                return saveEvent(event);
            }
            Event eventInDB = eventRepository.findOne(event.getId());
            System.out.println("before adding event ==="+eventInDB.getActor().toString());
            return new ErrorOperationResult(null, "event with id of " + event.getId() + " already exists", 400);
        }
        return saveEvent(event);
    }

    private OperationResult saveEvent(Event event) {
        Actor actor = event.getActor();
        Long actorID = actor.getId();
        if (Objects.nonNull(actorID)) {
            if (!actorRepository.exists(actorID)) {

                Actor actorSaved = actorRepository.save(actor);
                System.out.println(actorSaved);
            } else {
                event.setActor(actorRepository.findOne(actorID));
            }
        }

        Repo repo = event.getRepo();
        Long repoID = repo.getId();
        if (Objects.nonNull(repoID)) {
            if (!repoRepository.exists(repoID)) {

                Repo repoSaved = repoRepository.save(repo);
                System.out.println(repoSaved);
            } else {
                event.setRepo(repoRepository.findOne(repoID));
            }
        }
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
    public OperationResult getAllEventsOrderedByIdAscending() {
        List<Event> eventList = eventRepository.findAllByOrderByIdAsc();
        return new SuccessfulOperationResult(eventList, "Success", 200);
    }

    public OperationResult getAllEventsByActorOrderByIdAscending(Long actorId) {
        if (actorRepository.exists(actorId)) {
            List<Event> eventList = eventRepository.findAllByActor_IdOrderByIdAsc(actorId);
            return new SuccessfulOperationResult(eventList, "Success", 200);
        }
        return new ErrorOperationResult(null, "Actor with id=" + actorId + " does not exist", 404);
    }
}
