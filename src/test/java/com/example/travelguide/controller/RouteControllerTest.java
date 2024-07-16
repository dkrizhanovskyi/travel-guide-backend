package com.example.travelguide.controller;

import com.example.travelguide.model.Route;
import com.example.travelguide.service.RouteService;
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

class RouteControllerTest {

    @Mock
    private RouteService routeService;

    @InjectMocks
    private RouteController routeController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(routeController).build();
    }

    @Test
    void getAllRoutes() throws Exception {
        when(routeService.getAllRoutes()).thenReturn(Collections.singletonList(new Route(1L, "Route 1", "Description 1")));

        mockMvc.perform(get("/routes")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Route 1"))
                .andDo(result -> {
                    System.out.println("Response: " + result.getResponse().getContentAsString());
                });
    }

    @Test
    void createRoute() throws Exception {
        Route route = new Route(1L, "Route 1", "Description 1");
        when(routeService.saveRoute(any(Route.class))).thenReturn(route);

        mockMvc.perform(post("/routes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Route 1\",\"description\":\"Description 1\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Route 1"));
    }

    @Test
    void updateRoute() throws Exception {
        Route route = new Route(1L, "Route 1", "Description 1");
        when(routeService.updateRoute(any(Long.class), any(Route.class))).thenReturn(route);

        mockMvc.perform(put("/routes/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Route 1\",\"description\":\"Description 1\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Route 1"));
    }

    @Test
    void deleteRoute() throws Exception {
        mockMvc.perform(delete("/routes/1"))
                .andExpect(status().isNoContent());
    }
}
