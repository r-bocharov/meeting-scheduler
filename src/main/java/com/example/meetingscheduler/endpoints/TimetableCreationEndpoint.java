package com.example.meetingscheduler.endpoints;

import com.example.meetingscheduler.services.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class TimetableCreationEndpoint {

    private BookingService bookingService;

    @Autowired
    public TimetableCreationEndpoint(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @RequestMapping(path = "timetable-creation", method = RequestMethod.POST, consumes = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> processRequest(@Valid @RequestBody() String request) {
        return bookingService.processBookingRequest(request)
                .map(result -> ResponseEntity.ok().body(result.toJson()))
                .orElseGet(() -> ResponseEntity.badRequest().body("Couldn't process request"));
    }

}
