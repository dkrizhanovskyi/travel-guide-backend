package com.example.travelguide.controller;

import com.example.travelguide.exception.ResourceNotFoundException;
import com.example.travelguide.model.Destination;
import com.example.travelguide.service.DestinationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(DestinationController.class)
public class DestinationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DestinationService destinationService;

    @BeforeEach
    void setUp() {
        // MockMvc инициализируется автоматически благодаря аннотации @WebMvcTest
    }

    @Test
    void testGetAllDestinations() throws Exception {
        Destination destination1 = new Destination();
        destination1.setId(1L);
        destination1.setName("Paris");

        Destination destination2 = new Destination();
        destination2.setId(2L);
        destination2.setName("New York");

        List<Destination> destinations = Arrays.asList(destination1, destination2);

        Mockito.when(destinationService.getAllDestinations()).thenReturn(destinations);

        mockMvc.perform(get("/destinations"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(destinations.size()))
                .andExpect(jsonPath("$[0].name").value(destination1.getName()))
                .andExpect(jsonPath("$[1].name").value(destination2.getName()));
    }

    @Test
    void testCreateDestination() throws Exception {
        Destination destination = new Destination();
        destination.setName("Tokyo");

        Mockito.when(destinationService.saveDestination(any(Destination.class))).thenReturn(destination);

        mockMvc.perform(post("/destinations")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Tokyo\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value(destination.getName()));
    }

    @Test
    void testUpdateDestination() throws Exception {
        Destination destination = new Destination();
        destination.setId(1L);
        destination.setName("Berlin");

        Mockito.when(destinationService.updateDestination(anyLong(), any(Destination.class))).thenReturn(destination);

        mockMvc.perform(put("/destinations/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Berlin\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(destination.getName()));
    }

    @Test
    void testUpdateDestinationNotFound() throws Exception {
        Mockito.when(destinationService.updateDestination(anyLong(), any(Destination.class))).thenThrow(new ResourceNotFoundException("Not Found"));

        mockMvc.perform(put("/destinations/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Berlin\"}"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testDeleteDestination() throws Exception {
        Mockito.doNothing().when(destinationService).deleteDestination(anyLong());

        mockMvc.perform(delete("/destinations/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    void testDeleteDestinationNotFound() throws Exception {
        Mockito.doThrow(new ResourceNotFoundException("Not Found")).when(destinationService).deleteDestination(anyLong());

        mockMvc.perform(delete("/destinations/1"))
                .andExpect(status().isNotFound());
    }
}
