package com.hackerrank.github.comparator;

import com.hackerrank.github.model.Actor;
import com.hackerrank.github.model.Event;

import java.util.Comparator;
import java.util.List;

/**
 * author: acerbk
 * Date: 2019-06-29
 * Time: 16:28
 * 6.Returning the actor records ordered by the total number of events:
 * * The service should be able to return the JSON array of all the actors sorted by
 * * the total number of associated events with each actor in descending order by the GET request at /actors.
 * * If there are more than one actors with the same number of events,
 * * then order them by the timestamp of the latest event in the descending order.
 * * If more than one actors have the same timestamp for the latest event,
 * * then order them by the alphabetical order of login. The HTTP response code should be 200.
 * <p>
 * <p>
 */
public class ActorDefaultSortingComparator implements Comparator<Actor> {

    /**
     * -1= actor1 less than actor2
     * 0= actor1 equal to actor2
     * 1= actor1 greater than actor2
     * but since we need it descending order,we return numbers in vice versa format except 0 is same
     *
     * @param actor1
     * @param actor2
     * @return
     */
    @Override
    public int compare(Actor actor1, Actor actor2) {
        List<Event> actorEvents1 = actor1.getEventList();

        List<Event> actorEvents2 = actor2.getEventList();

        int eventSizeOfActor1 = actorEvents1.size();
        int eventSizeOfActor2 = actorEvents2.size();

        //compare the number of elements in actor1 and actor 2,if actor1 has more elements,return 1.
        // if actor1 has less,return -1,but since we need it in descending order we go vice-versa.
        if (eventSizeOfActor1 > eventSizeOfActor2) {
            return -1; //greater than actor 2
        }
        if (eventSizeOfActor1 < eventSizeOfActor2) {
            return 1; //less than actor 2
        }

        return ActorTimeStampFurtherComparator.getInstance().compareTo(actor1, actor2);
    }
}
