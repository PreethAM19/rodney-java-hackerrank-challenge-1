package com.hackerrank.github.controller;

import com.hackerrank.github.datautil.OperationResult;
import com.hackerrank.github.model.Actor;
import com.hackerrank.github.service.ActorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * author: acerbk
 * Date: 2019-06-29
 * Time: 20:18
 */
@RestController
public class ActorsApiRestController {

    private final ActorService actorService;

    public ActorsApiRestController(ActorService actorService) {
        this.actorService = actorService;
    }

    /**
     * 5.Updating the avatar URL of the actor:
     * The service should be able to update the avatar URL of
     * the actor by the PUT request at /actors.
     * The actor JSON is sent in the request body.
     * If the actor with the id does not exist
     * then the response code should be 404,
     * or if there are other fields being updated for the actor
     * then the HTTP response code should be 400,
     * otherwise, the response code should be 200.
     *
     * @param actor
     * @return
     */
    @PutMapping("/actors")
    public ResponseEntity<?> updateActor(@RequestBody Actor actor) {
        OperationResult operationResult = actorService.updateActorEntity(actor);

        return ResponseEntity.status(operationResult.getStatusCode())
                .body(null);
    }

    /***
     * 6.Returning the actor records ordered by the total number of events:
     * The service should be able to return the JSON array of all the actors sorted by
     * the total number of associated events with each actor in descending order by the GET request at /actors.
     * If there are more than one actors with the same number of events,
     * then order them by the timestamp of the latest event in the descending order.
     * If more than one actors have the same timestamp for the latest event,
     * then order them by the alphabetical order of login. The HTTP response code should be 200.
     *
     * @return
     */
    @GetMapping("/actors")
    public ResponseEntity<?> getAllActorsByTotalNumberOfEvents() {
        OperationResult operationResult = actorService.getAllActorsByTotalNumberOfEvents();

        return ResponseEntity.status(operationResult.getStatusCode())
                .body(operationResult.getData());
    }

    /**
     * 7.Returning the actor records ordered by the maximum streak:
     * The service should be able to return the JSON array of all the actors
     * sorted by the maximum streak (i.e., the total number of consecutive days actor has pushed an event to the system)
     * in descending order by the GET request at /actors/streak.
     * If there are more than one actors with the same maximum streak,
     * then order them by the timestamp of the latest event in the descending order.
     * If more than one actors have the same timestamp for the latest event,
     * then order them by the alphabetical order of login.
     * The HTTP response code should be 200.
     *
     * @return
     */
    @GetMapping("/actors/streak")
    public ResponseEntity<?> getAllActorsByHighestStreak() {
        OperationResult operationResult = actorService.getAllActorsByHighestStreak();

        return ResponseEntity.status(operationResult.getStatusCode())
                .body(operationResult.getData());
    }
}
