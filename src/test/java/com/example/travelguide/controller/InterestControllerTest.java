package com.example.travelguide.controller;

import com.example.travelguide.model.Interest;
import com.example.travelguide.service.InterestService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(InterestController.class)
public class InterestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private InterestService interestService;

    @Test
    void testGetAllInterests() throws Exception {
        Interest interest = new Interest();
        interest.setId(1L);
        interest.setName("Hiking");

        Page<Interest> page = new PageImpl<>(Collections.singletonList(interest));
        Mockito.when(interestService.getAllInterests(any(PageRequest.class))).thenReturn(page);

        mockMvc.perform(get("/interests")
                        .param("page", "0")
                        .param("size", "10")
                        .param("sortBy", "name"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].name").value(interest.getName()));
    }

    @Test
    void testCreateInterest() throws Exception {
        Interest interest = new Interest();
        interest.setName("Skiing");

        Mockito.when(interestService.saveInterest(any(Interest.class))).thenReturn(interest);

        mockMvc.perform(post("/interests")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Skiing\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value(interest.getName()));
    }

    @Test
    void testUpdateInterest() throws Exception {
        Interest interest = new Interest();
        interest.setId(1L);
        interest.setName("Climbing");

        Mockito.when(interestService.updateInterest(anyLong(), any(Interest.class))).thenReturn(interest);

        mockMvc.perform(put("/interests/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Climbing\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(interest.getName()));
    }

    @Test
    void testDeleteInterest() throws Exception {
        Mockito.doNothing().when(interestService).deleteInterest(anyLong());

        mockMvc.perform(delete("/interests/1"))
                .andExpect(status().isNoContent());
    }
}
