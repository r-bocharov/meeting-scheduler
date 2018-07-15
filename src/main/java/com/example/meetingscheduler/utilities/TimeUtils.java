package com.example.meetingscheduler.utilities;

import com.example.meetingscheduler.model.Booking;
import com.example.meetingscheduler.model.WorkHours;

import java.time.LocalTime;

public class TimeUtils {

    public static boolean isValid(Booking booking, WorkHours officeWorkHours) {
        final LocalTime officeHoursFrom = officeWorkHours.getFromTime();
        final LocalTime officeHoursTo = officeWorkHours.getToTime();

        final boolean isSameDay = booking.getTimeFrom().toLocalDate().compareTo(booking.getTimeTo().toLocalDate()) == 0;

        final boolean isStartWithinOfficeHours = compareTimeRangeInclusive(booking.getTimeFrom().toLocalTime(),
                officeHoursFrom, officeHoursTo);

        final boolean isEndWithinOfficeHours = compareTimeRangeInclusive(booking.getTimeTo().toLocalTime(),
                officeHoursFrom, officeHoursTo);

        return isSameDay && isStartWithinOfficeHours && isEndWithinOfficeHours;
    }

    public static boolean checkOverlap(LocalTime thisTimeFrom, LocalTime thisTimeTo,
                                       LocalTime thatTimeFrom, LocalTime thatTimeTo) {

        return compareTimeRangeInclusiveStart(thisTimeFrom, thatTimeFrom, thatTimeTo) ||
                compareTimeRangeInclusiveEnd(thisTimeTo, thatTimeFrom, thatTimeTo) ||
                compareTimeRangeInclusiveStart(thatTimeFrom, thisTimeFrom, thisTimeTo) ||
                compareTimeRangeInclusiveEnd(thatTimeTo, thisTimeFrom, thisTimeTo);
    }

    private static boolean compareTimeRangeInclusive(LocalTime time, LocalTime rangeFrom, LocalTime rangeTo) {
        return (time.compareTo(rangeFrom) == 0 || time.isAfter(rangeFrom)) &&
                (time.compareTo(rangeTo) == 0 || time.isBefore(rangeTo));
    }

    private static boolean compareTimeRangeInclusiveStart(LocalTime time, LocalTime rangeFrom, LocalTime rangeTo) {
        return (time.compareTo(rangeFrom) == 0 || time.isAfter(rangeFrom)) && time.isBefore(rangeTo);
    }

    private static boolean compareTimeRangeInclusiveEnd(LocalTime time, LocalTime rangeFrom, LocalTime rangeTo) {
        return time.isAfter(rangeFrom) && (time.compareTo(rangeTo) == 0 || time.isBefore(rangeTo));
    }
}
