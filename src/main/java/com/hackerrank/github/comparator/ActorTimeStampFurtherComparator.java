package com.hackerrank.github.comparator;

import com.hackerrank.github.model.Actor;
import com.hackerrank.github.model.Event;
import com.hackerrank.github.util.DateUtil;

import java.sql.Timestamp;
import java.util.List;

/**
 * author: acerbk
 * Date: 2019-06-30
 * Time: 21:11
 */
public class ActorTimeStampFurtherComparator {

    private static ActorTimeStampFurtherComparator ourInstance = new ActorTimeStampFurtherComparator();

    public static ActorTimeStampFurtherComparator getInstance() {
        return ourInstance;
    }

    private ActorTimeStampFurtherComparator() {
    }

    /**
     * this custom comparator compares in the following order :
     * 1. Compares timestamps of actors and order timestamp of the latest event in the descending order.
     * 2. if two timestamps are same for latest event,then order them by the alphabetical order of login.
     *
     * @param actor1
     * @param actor2
     * @return -1 if Actor1 is lesser than Actor2 ,or return 1 if Actor1>Actor2
     */
    public int compareTo(Actor actor1, Actor actor2) {

        List<Event> actorEvents1 = actor1.getEventList();
        List<Event> actorEvents2 = actor2.getEventList();

        // it's the same number of events,therefore order further by timestamp
        List<Timestamp> timestampsOfActor1 = DateUtil.getInstance().getTimeStampsOfEventList(actorEvents1);
        Timestamp maxTimestampOfActor1 = DateUtil.getInstance().getMaxTimestamp(timestampsOfActor1);

        List<Timestamp> timestampsOfActor2 = DateUtil.getInstance().getTimeStampsOfEventList(actorEvents2);
        Timestamp maxTimestampOfActor2 = DateUtil.getInstance().getMaxTimestamp(timestampsOfActor2);

        int resultOfComparingMaxTimestampsOfBothActors = maxTimestampOfActor1.compareTo(maxTimestampOfActor2);

        //now since comparing both maximum timestamps and they are the same,we use the login names to compare
        if (resultOfComparingMaxTimestampsOfBothActors == 0) {

            String loginNameActor1 = actor1.getLogin().trim();
            String loginNameActor2 = actor2.getLogin().trim();

            //finally we compare the strings ignoring case and since the login name is unique,
            // we can be sure that the list will be sorted alphabetically perfectly
            return loginNameActor1.compareToIgnoreCase(loginNameActor2);
        }
        return resultOfComparingMaxTimestampsOfBothActors; //it will be greater than or equal so we return it
    }
}
