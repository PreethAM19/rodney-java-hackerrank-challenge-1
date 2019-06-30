package com.hackerrank.github.comparator;

import com.hackerrank.github.model.Actor;
import com.hackerrank.github.model.Event;


import java.sql.Timestamp;
import java.util.ArrayList;
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
public class ActorSortingComparator implements Comparator<Actor> {

    /**
     * -1= actor1 less than actor2
     * 0= actor1 equal to actor2
     * 1= actor1 greater than actor2
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
        // if actor1 has less,return -1
        if (eventSizeOfActor1 > eventSizeOfActor2) {
            return 1; //greater than actor 2
        }
        if (eventSizeOfActor1 < eventSizeOfActor2) {
            return -1; //less than actor 2
        }

            // it's the same number of events,therefore order further by timestamp
            List<Timestamp> timestampsOfActor1 = getTimeStampsOfEventList(actorEvents1);
            Timestamp maxTimestampOfActor1 = getMaxTimestamp(timestampsOfActor1);

            List<Timestamp> timestampsOfActor2 = getTimeStampsOfEventList(actorEvents2);
            Timestamp maxTimestampOfActor2 = getMaxTimestamp(timestampsOfActor2);

            int resultOfComparingMaxTimestampsOfBothActors = maxTimestampOfActor1.compareTo(maxTimestampOfActor2);

            //now since comparing both maximum timestamps and they are the same,we use the login names to compare
            if (resultOfComparingMaxTimestampsOfBothActors == 0) {

                String loginNameActor1 = actor1.getLogin().trim();
                String loginNameActor2 = actor2.getLogin().trim();

                //finally we compare the strings ignoring case and since the login name is unique,
                // we can be sure that the list will be sorted perfectly
                return loginNameActor1.compareToIgnoreCase(loginNameActor2);
            }
            return resultOfComparingMaxTimestampsOfBothActors; //it will be greater than or equal so we return it
    }

    private Timestamp getMaxTimestamp(List<Timestamp> timestampsList) {
        return timestampsList.stream().max(Timestamp::compareTo).get();
    }

    private List<Timestamp> getTimeStampsOfEventList(List<Event> eventsList) {
        List<Timestamp> timestampList = new ArrayList<>();
        eventsList.forEach(event -> timestampList.add(event.getCreatedAt()));
        return timestampList;
    }
}
