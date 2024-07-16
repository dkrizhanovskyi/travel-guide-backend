package com.example.travelguide.controller;

import com.example.travelguide.model.Interest;
import com.example.travelguide.service.InterestService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class InterestControllerTest {

    @Mock
    private InterestService interestService;

    @InjectMocks
    private InterestController interestController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(interestController).build();
    }

    @Test
    void getAllInterests() throws Exception {
        when(interestService.getAllInterests()).thenReturn(Collections.singletonList(new Interest(1L, "Hiking", "Mountain hiking")));

        mockMvc.perform(get("/interests"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Hiking"));
    }

    @Test
    void createInterest() throws Exception {
        Interest interest = new Interest(1L, "Hiking", "Mountain hiking");
        when(interestService.saveInterest(any(Interest.class))).thenReturn(interest);

        mockMvc.perform(post("/interests")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Hiking\",\"description\":\"Mountain hiking\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Hiking"));
    }

    @Test
    void updateInterest() throws Exception {
        Interest interest = new Interest(1L, "Hiking", "Mountain hiking");
        when(interestService.updateInterest(any(Long.class), any(Interest.class))).thenReturn(interest);

        mockMvc.perform(put("/interests/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Hiking\",\"description\":\"Mountain hiking\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Hiking"));
    }

    @Test
    void deleteInterest() throws Exception {
        mockMvc.perform(delete("/interests/1"))
                .andExpect(status().isNoContent());
    }
}
