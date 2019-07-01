package com.hackerrank.github.util;

import com.hackerrank.github.exception.SameDayException;
import com.hackerrank.github.model.Event;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * author: acerbk
 * Date: 2019-06-30
 * Time: 18:40
 */
public class DateUtil {

    public static final String TIME_ZONE = "UTC";

    private static DateUtil ourInstance = new DateUtil();

    public static DateUtil getInstance() {
        return ourInstance;
    }

    private DateUtil() {
    }

    /**
     * get events timestamps list
     *
     * @param eventsList
     * @return
     */
    public List<Timestamp> getTimeStampsOfEventList(List<Event> eventsList) {
        List<Timestamp> timestampList = new ArrayList<>();
        eventsList.forEach(event -> timestampList.add(event.getCreatedAt()));
        return timestampList;
    }

    /**
     * find latest time from list of timestamps
     *
     * @param timestampsList
     * @return
     */
    public Timestamp getMaxTimestamp(List<Timestamp> timestampsList) {
        return timestampsList.stream().max(Timestamp::compareTo).get();
    }

    /**
     * find oldest time from list of timestamps.
     *
     * @param timestampsList
     * @return
     */
    public Timestamp getMinTimestamp(List<Timestamp> timestampsList) {
        return timestampsList.stream().min(Timestamp::compareTo).get();
    }

    /**
     * get the number of days difference between two timestamps,
     * throw SameDayException if the two timestamps refer to the same day.
     *
     * @param timestampMinimum
     * @param timestampMaximum
     * @return
     * @throws SameDayException
     */
    public long getDifferenceInNumberOfDays(Timestamp timestampMinimum, Timestamp timestampMaximum) throws SameDayException {
        Date startDate = new Date(timestampMinimum.getTime());
        Date endDate = new Date(timestampMaximum.getTime());

        long numberOfDays = ChronoUnit.DAYS.between(LocalDateTime.ofInstant(startDate.toInstant(), ZoneId.systemDefault()),
                LocalDateTime.ofInstant(endDate.toInstant(), ZoneId.systemDefault()));
        Math.abs(numberOfDays);//pick absolute value to ensure we always get a positive number instead of negative in some cases


        if (numberOfDays == 0) {
            throw new SameDayException("There is no difference in timestamps provided.They refer to the same day");
        }
        return numberOfDays + 1;
    }
}
