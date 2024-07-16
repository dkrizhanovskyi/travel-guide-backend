package com.example.travelguide;

import com.example.travelguide.controller.DestinationController;
import com.example.travelguide.model.Destination;
import com.example.travelguide.repository.DestinationRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(DestinationController.class)
public class DestinationControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private DestinationRepository repository;

    @Test
    public void getAllDestinations() throws Exception {
        Destination destination = new Destination(1L, "Paris", "City of Lights", "http://example.com/paris.jpg");
        List<Destination> allDestinations = Arrays.asList(destination);

        given(repository.findAll()).willReturn(allDestinations);

        mvc.perform(get("/api/destinations")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value(destination.getName()));
    }
}
