package com.example.travelguide.service;

import com.example.travelguide.exception.ResourceNotFoundException;
import com.example.travelguide.model.Interest;
import com.example.travelguide.repository.InterestRepository;
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

public class InterestServiceTest {

    @Mock
    private InterestRepository interestRepository;

    @InjectMocks
    private InterestService interestService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void getAllInterestsTest() {
        Interest interest1 = new Interest(1L, "Hiking", "Exploring trails and nature");
        Interest interest2 = new Interest(2L, "Photography", "Capturing moments");

        when(interestRepository.findAll()).thenReturn(Arrays.asList(interest1, interest2));

        List<Interest> interests = interestService.getAllInterests();

        assertEquals(2, interests.size());
        assertEquals("Hiking", interests.get(0).getName());
        assertEquals("Photography", interests.get(1).getName());
    }

    @Test
    public void getInterestByIdNotFoundTest() {
        Long id = 1L;
        when(interestRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            interestService.updateInterest(id, new Interest());
        });
    }

    @Test
    public void deleteInterestNotFoundTest() {
        Long id = 1L;
        when(interestRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            interestService.deleteInterest(id);
        });
    }
}
