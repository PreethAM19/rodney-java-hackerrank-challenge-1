package com.hackerrank.github.service;

import com.hackerrank.github.comparator.ActorMaximumStreakSortingComparator;
import com.hackerrank.github.comparator.ActorDefaultSortingComparator;
import com.hackerrank.github.datautil.ErrorOperationResult;
import com.hackerrank.github.datautil.OperationResult;
import com.hackerrank.github.datautil.SuccessfulOperationResult;
import com.hackerrank.github.model.Actor;
import com.hackerrank.github.repository.ActorRepository;
import org.springframework.stereotype.Service;

import javax.persistence.OptimisticLockException;
import java.util.*;


/**
 * author: acerbk
 * Date: 2019-06-29
 * Time: 13:25
 */
@Service
public class ActorService {

    private ActorRepository actorRepository;

    public ActorService(ActorRepository actorRepository) {
        this.actorRepository = actorRepository;
    }

    /**
     * * 7.Returning the actor records ordered by the maximum streak:
     * * The service should be able to return the JSON array of all the actors
     * * sorted by the maximum streak (i.e., the total number of consecutive days actor has pushed an event to the system)
     * * in descending order by the GET request at /actors/streak.
     * * If there are more than one actors with the same maximum streak,
     * * then order them by the timestamp of the latest event in the descending order.
     * * If more than one actors have the same timestamp for the latest event,
     * * then order them by the alphabetical order of login.
     * * The HTTP response code should be 200.
     *
     * @return
     */
    public OperationResult getAllActorsByHighestStreak() {
        List<Actor> actors = new ArrayList<>(0);
        actorRepository.findAll().forEach(actors::add);

        ActorMaximumStreakSortingComparator actorMaximumStreakSortingComparator = new ActorMaximumStreakSortingComparator();
        Collections.sort(actors, actorMaximumStreakSortingComparator);

        return new SuccessfulOperationResult(actors, "Successs", 200);
    }

    public OperationResult getAllActorsByTotalNumberOfEvents() {
        List<Actor> actors = new ArrayList<>(0);
        actorRepository.findAll().forEach(actors::add);

        ActorDefaultSortingComparator actorDefaultSortingComparator = new ActorDefaultSortingComparator();
        Collections.sort(actors, actorDefaultSortingComparator);

        //Stream.of(actors).sorted(actorDefaultSortingComparator.reversed());

        return new SuccessfulOperationResult(actors, "Successs", 200);
    }

    public OperationResult updateActorEntity(Actor actor) {
        if (Objects.nonNull(actor.getId())) {
            Long actorID = actor.getId();
            if (actorRepository.exists(actorID)) {
                try {
                    Actor finalActorToUpdate = updateActorFields(actor, actorRepository.findOne(actorID));
                    Actor actorUpdated = actorRepository.save(finalActorToUpdate);
                    return new SuccessfulOperationResult(actorUpdated, "successfully updated", 200);
                } catch (OptimisticLockException optimisticLockException) {
                    //actor is being currently updated thus return 400 status code
                    return new ErrorOperationResult(null, "Actor with id = " + actorID + " has other fields being currently updated!.Retry Later!.", 400);
                }
            }
            return new ErrorOperationResult(null, "Actor with id = " + actorID + " doesn't exist!.", 404);
        }
        return new ErrorOperationResult(null, "Id field does not exist", 404);
    }

    private Actor updateActorFields(Actor actorEntityToUpdate, Actor actorDbVersion) {
        if (Objects.nonNull(actorEntityToUpdate.getAvatarUrl())) {
            actorDbVersion.setAvatarUrl(actorEntityToUpdate.getAvatarUrl());
        }
        return actorDbVersion;
    }
}
