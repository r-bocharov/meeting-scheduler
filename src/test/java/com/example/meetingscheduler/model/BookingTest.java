package com.example.meetingscheduler.model;

import org.junit.Assert;
import org.junit.Test;

public class BookingTest {
    @Test
    public void fieldsSetCorrectly() {
        //given
        String requestDate = "2018-05-17";
        String requestTime = "10:17:06";
        String employeeId = "EMP001";
        SubmissionRequest submissionRequest = SubmissionRequest.parse(String.format("%s %s %s", requestDate, requestTime, employeeId), " ");

        String meetingDate = "2018-05-21";
        String meetingFrom = "09:00";
        Long duration = 2L;
        MeetingDurationRequest meetingDurationRequest = MeetingDurationRequest.parse(String.format("%s %s %s", meetingDate, meetingFrom, duration.toString()), " ");

        //when
        Booking booking = new Booking(submissionRequest, meetingDurationRequest);

        //then
        Assert.assertEquals(duration.longValue(), booking.getDuration());
        Assert.assertEquals(employeeId, booking.getEmployeeId());
        Assert.assertEquals(meetingDate + "T" + meetingFrom, booking.getTimeFrom().toString());
        Assert.assertEquals(meetingDate + "T11:00", booking.getTimeTo().toString());
        Assert.assertEquals(requestDate + "T" + requestTime, booking.getRequestDateTime().toString());
    }
}