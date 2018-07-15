package com.example.meetingscheduler.model;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Immutable class for submission request data
 * Example: "2018-05-17 10:17:06 EMP001"
 */
public class SubmissionRequest {

    private final LocalDate requestDate;
    private final LocalTime requestTime;
    private final String employeeId;

    public SubmissionRequest(String requestDate, String requestTime, String employeeId) {
        this.requestDate = LocalDate.parse(requestDate);
        this.requestTime = LocalTime.parse(requestTime);
        this.employeeId = employeeId;
    }

    public LocalDate getRequestDate() {
        return requestDate;
    }

    public LocalTime getRequestTime() {
        return requestTime;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    /**
     * Parse string with submission data
     * @param request data in format "2018-05-17 10:17:06 EMP001". Which consists of "data time employeeId" .
     * @param delimiter delimiter for input data
     * @return instance of this class
     */
    public static SubmissionRequest parse(String request, String delimiter) {
        final String[] submissionTimeValues = request.split(delimiter);
        final String requestDate = submissionTimeValues[0];
        final String requestTime = submissionTimeValues[1];
        final String employeeId = submissionTimeValues[2];

        return new SubmissionRequest(requestDate, requestTime, employeeId);
    }
}
