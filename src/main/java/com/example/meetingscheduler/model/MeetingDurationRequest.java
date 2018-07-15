package com.example.meetingscheduler.model;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Immutable class for meeting duration request data
 * Example: "2018-05-21 09:00 2"
 */
public class MeetingDurationRequest {

    private final LocalDate meetingDate;
    private final LocalTime meetingTime;
    private final long meetingDuration;

    public MeetingDurationRequest(String meetingDate, String meetingTime, String meetingDuration) {
        this.meetingDate = LocalDate.parse(meetingDate);
        this.meetingTime = LocalTime.parse(meetingTime);
        this.meetingDuration = Long.parseLong(meetingDuration);
    }

    public LocalDate getMeetingDate() {
        return meetingDate;
    }

    public LocalTime getMeetingTime() {
        return meetingTime;
    }

    public long getMeetingDuration() {
        return meetingDuration;
    }

    /**
     * Parse string with meeting duration data
     * @param request data in format "2018-05-21 09:00 2". Which consists of "data time numberOfHours".
     * @param delimiter delimiter for input data
     * @return instance of this class
     */
    public static MeetingDurationRequest parse(String request, String delimiter) {
        final String[] meetingDurationValues = request.split(delimiter);
        final String meetingDate = meetingDurationValues[0];
        final String meetingTime = meetingDurationValues[1];
        final String meetingDuration = meetingDurationValues[2];

        return new MeetingDurationRequest(meetingDate, meetingTime, meetingDuration);
    }
}