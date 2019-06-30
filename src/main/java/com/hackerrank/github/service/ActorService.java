package com.hackerrank.github.service;

import com.hackerrank.github.comparator.ActorSortingComparator;
import com.hackerrank.github.datautil.ErrorOperationResult;
import com.hackerrank.github.datautil.OperationResult;
import com.hackerrank.github.datautil.SuccessfulOperationResult;
import com.hackerrank.github.model.Actor;
import com.hackerrank.github.repository.ActorRepository;
import org.springframework.stereotype.Service;

import javax.persistence.OptimisticLockException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

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

    public OperationResult getAllActors() {
        List<Actor> actors = new ArrayList<>(0);
        actorRepository.findAll().forEach(actors::add);

        ActorSortingComparator actorSortingComparator = new ActorSortingComparator();
        Collections.sort(actors, actorSortingComparator);

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
