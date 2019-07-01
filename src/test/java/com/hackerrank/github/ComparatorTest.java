package com.hackerrank.github;

import com.hackerrank.github.comparator.ActorMaximumStreakSortingComparator;
import com.hackerrank.github.model.Actor;
import com.hackerrank.github.model.Event;
import com.hackerrank.github.util.DateUtil;
import org.junit.Before;
import org.junit.Test;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static junit.framework.TestCase.assertSame;
import static junit.framework.TestCase.assertTrue;

/**
 * author: acerbk
 * Date: 2019-07-01
 * Time: 02:24
 */
public class ComparatorTest {

    List<Actor> actorList = null;

    @Before
    public void setupData() {
        Event event1Actor1 = new Event();
        event1Actor1.setCreatedAt(convertToTimeStamp("2016-04-18 00:13:31.0"));

        Event event1Actor2 = new Event();
        event1Actor2.setCreatedAt(convertToTimeStamp("2016-04-18 00:13:31.0"));

        Event event1Actor3 = new Event();
        event1Actor3.setCreatedAt(convertToTimeStamp("2016-04-18 00:13:31.0"));

        List<Event> eventListActor1 = Arrays.asList(event1Actor1, event1Actor2, event1Actor3);

        Actor actor1 = new Actor(22L, "rodney1", "http://whatever");
        actor1.setEventList(eventListActor1);


        Event eventActor11 = new Event();
        eventActor11.setCreatedAt(convertToTimeStamp("2016-04-18 00:13:31.0"));

        Event eventActor22 = new Event();
        eventActor22.setCreatedAt(convertToTimeStamp("2016-04-18 00:13:31.0"));

        Event eventActor33 = new Event();
        eventActor33.setCreatedAt(convertToTimeStamp("2016-04-18 00:13:31.0"));

        List<Event> eventListActor2 = Arrays.asList(eventActor11, eventActor22, eventActor33);

        Actor actor2 = new Actor(45L, "rodney29", "http://whatever");
        actor2.setEventList(eventListActor2);


        Event eventActor44 = new Event();
        eventActor44.setCreatedAt(convertToTimeStamp("2016-04-18 00:13:31.0"));

        Event eventActor55 = new Event();
        eventActor55.setCreatedAt(convertToTimeStamp("2016-04-18 00:13:31.0"));

        List<Event> eventListActor3 = Arrays.asList(eventActor44, eventActor55);

        Actor actor3 = new Actor(19L, "rodney5", "http://whatever");
        actor3.setEventList(eventListActor3);


        actorList = Arrays.asList(actor1, actor2, actor3);
    }

    @Test
    public void test_actor_maximum_streak_comparator_when_login_must_be_used_for_sorting() {


        System.out.println(" Before Sort------->\n\t\t");
        actorList.forEach(actor -> {
            System.out.println(actor);
        });

        Collections.sort(actorList, new ActorMaximumStreakSortingComparator());

        System.out.println("\n After Sort------->\n\t\t");
        actorList.forEach(actor -> {
            System.out.println(actor);
        });

        assertTrue((actorList.get(0).getLogin() == "rodney1") &&
                (actorList.get(1).getLogin() == "rodney29") &&
                (actorList.get(2).getLogin() == "rodney5"));

    }

    @Test
    public void test_actor_maximum_streak_comparator_when_highest_event_timestamp_must_be_used_for_sorting() {

        actorList.get(0).getEventList().get(0).setCreatedAt(convertToTimeStamp("2016-04-20 00:13:31.0"));
        actorList.get(2).getEventList().get(0).setCreatedAt(convertToTimeStamp("2016-04-19 00:13:31.0"));


        System.out.println(" Before Sort------->\n\t\t");
        actorList.forEach(actor -> {
            System.out.println(actor);
            actor.getEventList().forEach(event -> {
                System.out.println("\n\t\t"+event.getCreatedAt());
            });
        });

        Collections.sort(actorList, new ActorMaximumStreakSortingComparator());

        System.out.println("\n After Sort------->\n\t\t");
        actorList.forEach(actor -> {
            System.out.println(actor);
            actor.getEventList().forEach(event -> {
                System.out.println("\n\t\t"+event.getCreatedAt());
            });
        });

        assertTrue((actorList.get(0).getLogin() == "rodney5") &&
                (actorList.get(1).getLogin() == "rodney1") &&
                (actorList.get(2).getLogin() == "rodney29"));

    }

    @Test
    public void test_actor_maximum_streak_comparator_when_max_streak_must_be_used_for_sorting() {

        actorList.get(0).getEventList().get(0).setCreatedAt(convertToTimeStamp("2016-04-18 00:13:31.0"));
        actorList.get(0).getEventList().get(1).setCreatedAt(convertToTimeStamp("2016-04-18 00:13:31.0"));
        actorList.get(0).getEventList().get(2).setCreatedAt(convertToTimeStamp("2016-04-18 00:13:31.0"));

        actorList.get(1).getEventList().get(0).setCreatedAt(convertToTimeStamp("2016-04-14 00:13:31.0"));
        actorList.get(1).getEventList().get(1).setCreatedAt(convertToTimeStamp("2016-04-13 00:13:31.0"));
        actorList.get(1).getEventList().get(2).setCreatedAt(convertToTimeStamp("2016-04-12 00:13:31.0"));

        actorList.get(2).getEventList().get(0).setCreatedAt(convertToTimeStamp("2016-03-19 00:13:31.0"));
        actorList.get(2).getEventList().get(1).setCreatedAt(convertToTimeStamp("2016-03-18 00:13:31.0"));


        System.out.println(" Before Sort------->\n\t\t");
        actorList.forEach(actor -> {
            System.out.println(actor);
//            for (Event event : actor.getEventList()) {
//                System.out.println("\n date ==" + event.getCreatedAt().toString());
//            }
        });

        Collections.sort(actorList, new ActorMaximumStreakSortingComparator());

        System.out.println("\n After Sort------->\n\t\t");
        actorList.forEach(actor -> {
            System.out.println("\n" + actor);
        });

        assertTrue((actorList.get(0).getLogin() == "rodney29") &&
                (actorList.get(1).getLogin() == "rodney5") &&
                (actorList.get(2).getLogin() == "rodney1"));

    }

    @Test
    public void findLatestTime() {
        List<Timestamp> arrayTimes = Arrays.asList(convertToTimeStamp("2016-03-19 00:13:30.0"),
                convertToTimeStamp("2016-03-19 00:13:31.0"),
                convertToTimeStamp("2016-03-19 00:13:31.0"));
        assertSame(DateUtil.getInstance().getMaxTimestamp
                (arrayTimes), arrayTimes.get(1));
    }


    private static Timestamp convertToTimeStamp(String yourString) {
        Timestamp timestamp = null;
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            Date parsedDate = dateFormat.parse(yourString);
            timestamp = new java.sql.Timestamp(parsedDate.getTime());
        } catch (Exception e) { //this generic but you can control another types of exception
            // look the origin of excption
            System.out.println("exception found");
        }

        return timestamp;
    }

}
