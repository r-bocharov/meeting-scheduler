package com.example.meetingscheduler.model;

import java.time.LocalTime;

/**
 * Immutable class representing office for hours
 */
public class WorkHours {

    private LocalTime fromTime;
    private LocalTime toTime;

    public WorkHours(LocalTime fromTime, LocalTime toTime) {
        this.fromTime = fromTime;
        this.toTime = toTime;
    }

    public LocalTime getFromTime() {
        return fromTime;
    }

    public LocalTime getToTime() {
        return toTime;
    }
}
