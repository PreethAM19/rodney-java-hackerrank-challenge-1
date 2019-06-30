package com.hackerrank.github.comparator;

import com.hackerrank.github.exception.SameDayException;
import com.hackerrank.github.model.Actor;
import com.hackerrank.github.model.Event;
import com.hackerrank.github.util.DateUtil;

import java.sql.Timestamp;
import java.util.*;

/**
 * author: acerbk
 * Date: 2019-06-30
 * Time: 18:30
 */
public class ActorMaximumStreakSortingComparator implements Comparator<Actor> {

    @Override
    public int compare(Actor actor1, Actor actor2) {
        List<Event> eventListActor1 = actor1.getEventList();
        List<Event> eventListActor2 = actor2.getEventList();

        int maxStreakOfActor1 = findMaxStreak(eventListActor1, DateUtil.getInstance().TIME_ZONE);
        int maxStreakOfActor2 = findMaxStreak(eventListActor2, DateUtil.getInstance().TIME_ZONE);

        if (maxStreakOfActor1 == maxStreakOfActor2) {

            return ActorTimeStampFurtherComparator.getInstance().compareTo(actor1, actor2); //same number of maximum streaks ,pass to TimeSorting Comparator
        }

        return (maxStreakOfActor1 < maxStreakOfActor2) ? -1 : 1; //if maxStreak of Actor A is less than B's,return -1,if A>B,then return 1
    }

    private int findMaxStreak(List<Event> eventList, String timeZone) {
        List<Timestamp> timestampList = new ArrayList<>();
        eventList.forEach(event -> timestampList.add(event.getCreatedAt()));

        Timestamp minTimeStamp = DateUtil.getInstance().getMinTimestamp(timestampList);

        Timestamp maxTimeStamp = DateUtil.getInstance().getMaxTimestamp(timestampList);

        long numberOfDaysDifferenceBetweenMinAndMaxTimestamps;
        try {
            numberOfDaysDifferenceBetweenMinAndMaxTimestamps = new Long(
                    DateUtil.getInstance().getDifferenceInNumberOfDays(minTimeStamp, maxTimeStamp));
        } catch (SameDayException sameDayException) {
            System.out.println("same day return 1.........");
            return 1;//since 0 is being returned,we know that the different number of Days for the timestamps list is the same day so return 1
        }


        int maxStreakOfDays = findMaxStreakOfDays(minTimeStamp,
                maxTimeStamp,
                timestampList,
                numberOfDaysDifferenceBetweenMinAndMaxTimestamps,
                timeZone);

        return maxStreakOfDays;
    }

    private int findMaxStreakOfDays(Timestamp minTimeStamp,
                                    Timestamp maxTimeStamp,
                                    List<Timestamp> timestampList,
                                    long numberOfDaysDifferenceBetweenMinAndMaxTimestamps,
                                    String timeZone) {

        List<Integer> totalNumberOfStreaksForDates = new ArrayList<>(0);

        for (int i = 0; i < numberOfDaysDifferenceBetweenMinAndMaxTimestamps; i++) {

            Date dateToCompare = getDateAfterAddingNumberOfDays(minTimeStamp, i, timeZone);
            int noOfConsecutiveDays = getNumberOfConsecutiveDaysFromTimetampList(timestampList, dateToCompare, timeZone);
            totalNumberOfStreaksForDates.add(noOfConsecutiveDays);
        }

        int highestStreakOfDays = totalNumberOfStreaksForDates.stream().max(Integer::compareTo).get();
        return highestStreakOfDays;
    }

    /**
     * @param timestamp
     * @param numberOfDaysToAdd
     * @param timeZone
     * @return
     */
    private Date getDateAfterAddingNumberOfDays(Timestamp timestamp, int numberOfDaysToAdd, String timeZone) {
        if (numberOfDaysToAdd == 0) {// same day so return same Date
            return new Date(timestamp.getTime());
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(timestamp.getTime()));
        calendar.setTimeZone(TimeZone.getTimeZone(timeZone));
        calendar.add(Calendar.DATE, numberOfDaysToAdd);//ADD NUMBER OF DAYS

        Date dateToReturn = calendar.getTime();
        return dateToReturn;
    }

    private int getNumberOfConsecutiveDaysFromTimetampList(List<Timestamp> timestampList, Date dayToCompare, String timeZone) {
        Calendar calendarToCompare = prepareCalendarObjectFromDate(dayToCompare, timeZone);

        Integer totalStreakCount = 1; //default is 1 day

        for (int i = 0; i < timestampList.size(); i++) {
            Calendar calendarAtIndex = prepareCalendarObjectFromDate
                    (new Date(timestampList.get(i).getTime()), timeZone);

            //if the currentTimeStamp is in the same day as calendarToCompare,then we can move on
            // to check if the next timestamp also falls in same day,then we can increment totalStreakCount by 1.
            if (isInSameDay(calendarToCompare, calendarAtIndex)) {
                //we start comparing previous dates from location[1] since we are sure that there will be a previous
                //date to compare against in the list after index location 1,if current timestamp falls in same day as
                //previous timestamp,then that's a streak so we increment totalStreakCount by 1
                if (i > 0) {
                    int previousIndex = i - 1;
                    Calendar calendarAtPreviousIndex = prepareCalendarObjectFromDate
                            (new Date(timestampList.get(previousIndex).getTime()), timeZone);

                    if (isInSameDay(calendarAtIndex, calendarAtPreviousIndex)) {
                        //now we can increase totalStreakCount By 1 since the previous timestamp falls in same day as current one
                        totalStreakCount = new Integer(totalStreakCount + 1);
                    }
                }
            }
        }
        return totalStreakCount; //we return totalSreakCount as 1,which is default,meaning one day only
    }

    private Calendar prepareCalendarObjectFromDate(Date date, String timeZone) {
        Calendar calendarToCompare = Calendar.getInstance();
        calendarToCompare.setTimeZone(TimeZone.getTimeZone(timeZone));
        calendarToCompare.setTime(date);
        return calendarToCompare;
    }

    /**
     * is the same Day?
     *
     * @param cal1
     * @param cal2
     * @return
     */
    boolean isInSameDay(Calendar cal1, Calendar cal2) {
        boolean isSameDay = cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR) &&
                cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR);
        return isSameDay;
    }
}
