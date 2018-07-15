package com.example.meetingscheduler.utilities;

import com.example.meetingscheduler.model.Booking;
import com.example.meetingscheduler.model.MeetingDurationRequest;
import com.example.meetingscheduler.model.SubmissionRequest;
import com.example.meetingscheduler.model.WorkHours;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalTime;

public class TimeUtilsTest {

    @Test
    public void isValidTime() {
        //given
        SubmissionRequest submissionRequest = SubmissionRequest.parse("2018-05-17 10:17:06 EMP001", " ");
        MeetingDurationRequest meetingDurationRequest = MeetingDurationRequest.parse("2018-05-21 09:00 2", " ");
        Booking booking = new Booking(submissionRequest, meetingDurationRequest);
        WorkHours workHours = new WorkHours(LocalTime.parse("09:00"), LocalTime.parse("17:00"));

        //when
        boolean isValid = TimeUtils.isValid(booking, workHours);

        //then
        Assert.assertTrue("Not valid", isValid);
    }

    @Test
    public void isNotValidTime() {
        //given
        SubmissionRequest submissionRequest = SubmissionRequest.parse("2018-05-17 10:17:06 EMP001", " ");
        MeetingDurationRequest meetingDurationRequest = MeetingDurationRequest.parse("2018-05-21 09:00 2", " ");
        Booking booking = new Booking(submissionRequest, meetingDurationRequest);
        WorkHours workHours = new WorkHours(LocalTime.parse("12:00"), LocalTime.parse("13:00"));

        //when
        boolean isValid = TimeUtils.isValid(booking, workHours);

        //then
        Assert.assertFalse("valid", isValid);
    }

    @Test
    public void timeDoesNotOverlap() {
        //given
        LocalTime periodFrom = LocalTime.parse("10:00");
        LocalTime periodTo = LocalTime.parse("13:00");
        LocalTime period2From = LocalTime.parse("14:00");
        LocalTime period2To = LocalTime.parse("16:30");

        //when
        boolean result = TimeUtils.checkOverlap(periodFrom, periodTo, period2From, period2To);

        //then
        Assert.assertFalse("Time overlaps", result);
    }

    @Test
    public void timeOverlaps() {
        //given
        LocalTime periodFrom = LocalTime.parse("11:30");
        LocalTime periodTo = LocalTime.parse("15:30");
        LocalTime period2From = LocalTime.parse("13:30");
        LocalTime period2To = LocalTime.parse("17:30");

        //when
        boolean result1 = TimeUtils.checkOverlap(periodFrom, periodTo, period2From, period2To);

        //then
        Assert.assertTrue("Time does not overlap", result1);
    }
}