package com.example.travelguide.service;

import com.example.travelguide.exception.ResourceNotFoundException;
import com.example.travelguide.model.Destination;
import com.example.travelguide.repository.DestinationRepository;
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

public class DestinationServiceTest {

    @Mock
    private DestinationRepository destinationRepository;

    @InjectMocks
    private DestinationService destinationService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void getAllDestinationsTest() {
        Destination destination1 = new Destination(1L, "Paris", "City of Light");
        Destination destination2 = new Destination(2L, "London", "City of Fog");

        when(destinationRepository.findAll()).thenReturn(Arrays.asList(destination1, destination2));

        List<Destination> destinations = destinationService.getAllDestinations();

        assertEquals(2, destinations.size());
        assertEquals("Paris", destinations.get(0).getName());
        assertEquals("London", destinations.get(1).getName());
    }

    @Test
    public void getDestinationByIdNotFoundTest() {
        Long id = 1L;
        when(destinationRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            destinationService.updateDestination(id, new Destination());
        });
    }

    @Test
    public void deleteDestinationNotFoundTest() {
        Long id = 1L;
        when(destinationRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            destinationService.deleteDestination(id);
        });
    }
}
