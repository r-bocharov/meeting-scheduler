package com.example.meetingscheduler.model;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

/**
 * Immutable booking class
 */
public class Booking {

    private LocalDateTime timeFrom;
    private LocalDateTime timeTo;
    private String employeeId;
    private LocalDateTime requestDateTime;
    private long duration;

    public Booking(SubmissionRequest submissionTime, MeetingDurationRequest meetingDuration) {
        this.employeeId = submissionTime.getEmployeeId();
        this.timeFrom = LocalDateTime.of(meetingDuration.getMeetingDate(), meetingDuration.getMeetingTime());
        this.timeTo = timeFrom.plus(meetingDuration.getMeetingDuration(), ChronoUnit.HOURS);
        this.requestDateTime = LocalDateTime.of(submissionTime.getRequestDate(), submissionTime.getRequestTime());
        this.duration = meetingDuration.getMeetingDuration();
    }

    public LocalDateTime getTimeFrom() {
        return timeFrom;
    }

    public LocalDateTime getTimeTo() {
        return timeTo;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public LocalDateTime getRequestDateTime() {
        return requestDateTime;
    }

    public long getDuration() {
        return duration;
    }
}
