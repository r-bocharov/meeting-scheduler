package com.example.meetingscheduler.endpoints;

import org.hamcrest.CoreMatchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TimetableCreationEndpointTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void processRequestSuccessfully() throws Exception {

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

        this.mockMvc.perform(post("/timetable-creation")
                .contentType(MediaType.TEXT_PLAIN_VALUE)
                .content(request))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(CoreMatchers.containsString(response)));
    }

    @Test
    public void requestHasIncorrectInputData() throws Exception {
        String request = "incorrect request data";
        String response = "Couldn't process request";

        this.mockMvc.perform(post("/timetable-creation")
                .contentType(MediaType.TEXT_PLAIN_VALUE)
                .content(request))
                .andDo(print())
                .andExpect(status().is4xxClientError())
                .andExpect(content().string(response));
    }
}