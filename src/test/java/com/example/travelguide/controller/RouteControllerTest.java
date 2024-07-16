package com.example.travelguide.controller;

import com.example.travelguide.model.Route;
import com.example.travelguide.service.RouteService;
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
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(RouteController.class)
public class RouteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RouteService routeService;

    @Test
    void testGetAllRoutes() throws Exception {
        Route route = new Route();
        route.setId(1L);
        route.setName("Route 1");

        Page<Route> page = new PageImpl<>(Collections.singletonList(route));
        Mockito.when(routeService.getAllRoutes(any(PageRequest.class))).thenReturn(page);

        mockMvc.perform(get("/routes")
                        .param("page", "0")
                        .param("size", "10")
                        .param("sortBy", "name"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].name").value(route.getName()));
    }

    @Test
    void testCreateRoute() throws Exception {
        Route route = new Route();
        route.setName("New Route");

        Mockito.when(routeService.saveRoute(any(Route.class))).thenReturn(route);

        mockMvc.perform(post("/routes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"New Route\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value(route.getName()));
    }

    @Test
    void testUpdateRoute() throws Exception {
        Route route = new Route();
        route.setId(1L);
        route.setName("Updated Route");

        Mockito.when(routeService.updateRoute(anyLong(), any(Route.class))).thenReturn(route);

        mockMvc.perform(put("/routes/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Updated Route\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(route.getName()));
    }

    @Test
    void testDeleteRoute() throws Exception {
        Mockito.doNothing().when(routeService).deleteRoute(anyLong());

        mockMvc.perform(delete("/routes/1"))
                .andExpect(status().isNoContent());
    }
}
