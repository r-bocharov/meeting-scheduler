package com.example.meetingscheduler.services;

import com.example.meetingscheduler.model.Booking;
import com.example.meetingscheduler.model.BookingResult;
import com.example.meetingscheduler.model.MeetingDurationRequest;
import com.example.meetingscheduler.model.SubmissionRequest;
import com.example.meetingscheduler.model.WorkHours;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * Timetable booking service
 */
@Service
public class BookingService {

    private final String newLineDelimiter;
    private final String requestDelimiter;

    public BookingService(@Value("${input.line-delimiter}") String newLineDelimiter,
                          @Value("${input.request-delimiter}") String requestDelimiter) {
        this.newLineDelimiter = newLineDelimiter;
        this.requestDelimiter = requestDelimiter;
    }

    /**
     * Processes a list of booking requests
     * @param message booking request
     * @return Optional of booking result
     */
    public Optional<BookingResult> processBookingRequest(String message) {
        final List<String> values = Arrays.asList(message.split(newLineDelimiter));

        if (values.isEmpty()) {
            return Optional.empty();
        }

        Optional<WorkHours> officeWorkHours = processWorkHours(values.get(0));
        List<Booking> bookings = processBookings(values.subList(1, values.size()));

        if (!bookings.isEmpty() && officeWorkHours.isPresent()) {
            return Optional.of(new BookingResult(officeWorkHours.get(), bookings));
        }

        return Optional.empty();
    }

    private List<Booking> processBookings(List<String> values) {

        if (values.size() < 2) {
            return Collections.emptyList();
        }

        final List<Booking> bookings = new ArrayList<>();

        for (int num = 0; num < values.size(); num += 2) {
            String submissionTime = values.get(num);
            String meetingDuration = values.get(num + 1);

            if (submissionTime.isEmpty() || meetingDuration.isEmpty()) {
                continue;
            }

            final SubmissionRequest submissionRequest = SubmissionRequest.parse(submissionTime, requestDelimiter);
            final MeetingDurationRequest meetingDurationRequest = MeetingDurationRequest.parse(meetingDuration, requestDelimiter);

            bookings.add(new Booking(submissionRequest, meetingDurationRequest));
        }

        return bookings;
    }

    private Optional<WorkHours> processWorkHours(String rawHours) {
        final String[] hours = rawHours.split(" ");

        if (hours.length != 2 || hours[0].length() < 4 || hours[1].length() < 4) {
            return Optional.empty();
        }

        String startTime = hours[0].substring(0, 2) + ":" + hours[0].substring(2, 4);
        String endTime = hours[1].substring(0, 2) + ":" + hours[1].substring(2, 4);

        final WorkHours officeWorkHours = new WorkHours(LocalTime.parse(startTime), LocalTime.parse(endTime));
        return Optional.of(officeWorkHours);
    }
}
