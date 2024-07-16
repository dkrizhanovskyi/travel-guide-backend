package com.example.travelguide.controller;

import com.example.travelguide.model.Destination;
import com.example.travelguide.service.DestinationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class DestinationControllerTest {

    @Mock
    private DestinationService destinationService;

    @InjectMocks
    private DestinationController destinationController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(destinationController).build();
    }

    @Test
    void getAllDestinations() throws Exception {
        when(destinationService.getAllDestinations()).thenReturn(Collections.singletonList(new Destination(1L, "Paris", "City of Light")));

        mockMvc.perform(get("/destinations"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Paris"));
    }

    @Test
    void createDestination() throws Exception {
        Destination destination = new Destination(1L, "Paris", "City of Light");
        when(destinationService.saveDestination(any(Destination.class))).thenReturn(destination);

        mockMvc.perform(post("/destinations")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Paris\",\"description\":\"City of Light\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Paris"));
    }

    @Test
    void updateDestination() throws Exception {
        Destination destination = new Destination(1L, "Paris", "City of Light");
        when(destinationService.updateDestination(any(Long.class), any(Destination.class))).thenReturn(destination);

        mockMvc.perform(put("/destinations/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Paris\",\"description\":\"City of Light\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Paris"));
    }

    @Test
    void deleteDestination() throws Exception {
        mockMvc.perform(delete("/destinations/1"))
                .andExpect(status().isNoContent());
    }
}
