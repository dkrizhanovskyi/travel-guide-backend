package com.example.travelguide.service;

import com.example.travelguide.exception.ResourceNotFoundException;
import com.example.travelguide.model.Route;
import com.example.travelguide.repository.RouteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doThrow;

public class RouteServiceTest {

    @Mock
    private RouteRepository routeRepository;

    @InjectMocks
    private RouteService routeService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void getAllRoutesTest() {
        Route route1 = new Route(1L, "Mountain Trail", "Scenic mountain route");
        Route route2 = new Route(2L, "River Walk", "Beautiful river path");

        when(routeRepository.findAll()).thenReturn(Arrays.asList(route1, route2));

        List<Route> routes = routeService.getAllRoutes();

        assertEquals(2, routes.size());
        assertEquals("Mountain Trail", routes.get(0).getName());
        assertEquals("River Walk", routes.get(1).getName());
    }

    @Test
    public void getRouteByIdNotFoundTest() {
        Long id = 1L;
        when(routeRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            routeService.updateRoute(id, new Route());
        });
    }

    @Test
    public void deleteRouteNotFoundTest() {
        Long id = 1L;
        when(routeRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            routeService.deleteRoute(id);
        });
    }
}
