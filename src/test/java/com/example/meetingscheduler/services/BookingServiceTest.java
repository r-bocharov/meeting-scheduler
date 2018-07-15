package com.example.meetingscheduler.services;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookingServiceTest {

    @Autowired
    BookingService bookingService;

    @Test
    public void processBookingRequest() {
        //given
        String request = "0900 1730\n" +
                "2018-05-17 10:17:06 EMP001\n" +
                "2018-05-21 09:00 2\n" +
                "2018-05-16 12:34:56 EMP002\n" +
                "2018-05-21 09:00 2\n" +
                "2018-05-16 09:28:23 EMP003\n" +
                "2018-05-22 14:00 2\n" +
                "2018-05-17 11:23:45 EMP004\n" +
                "2018-05-22 16:00 1\n" +
                "2018-05-15 17:29:12 EMP005\n" +
                "2018-05-21 16:00 3\n" +
                "2018-05-30 17:29:12 EMP006\n" +
                "2018-05-21 10:00 3";

        String response = "2018-05-21\n" +
                "09:00 11:00 EMP002\n" +
                "2018-05-22\n" +
                "14:00 16:00 EMP003\n" +
                "16:00 17:00 EMP004\n";

        //when
        String actualResponse = bookingService.processBookingRequest(request).get().toJson();

        //then
        Assert.assertEquals(response, actualResponse);

    }
}