package com.example.travelguide.service;

import com.example.travelguide.exception.ResourceNotFoundException;
import com.example.travelguide.model.Destination;
import com.example.travelguide.repository.DestinationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class for managing destinations.
 */
@Service
public class DestinationService {

    @Autowired
    private DestinationRepository destinationRepository;

    /**
     * Retrieves all destinations.
     * @return a list of all destinations.
     */
    public List<Destination> getAllDestinations() {
        return destinationRepository.findAll();
    }

    /**
     * Retrieves all destinations with pagination.
     * @param pageRequest the pagination information.
     * @return a paginated list of destinations.
     */
    public Page<Destination> getAllDestinations(PageRequest pageRequest) {
        return destinationRepository.findAll(pageRequest);
    }

    /**
     * Retrieves destinations by name with pagination.
     * @param name the name to search for.
     * @param pageRequest the pagination information.
     * @return a paginated list of destinations matching the search criteria.
     */
    public Page<Destination> getDestinationsByName(String name, PageRequest pageRequest) {
        return destinationRepository.findByNameContaining(name, pageRequest);
    }

    /**
     * Saves a new destination.
     * @param destination the destination to save.
     * @return the saved destination.
     */
    public Destination saveDestination(Destination destination) {
        return destinationRepository.save(destination);
    }

    /**
     * Updates an existing destination.
     * @param id the ID of the destination to update.
     * @param destinationDetails the updated destination details.
     * @return the updated destination.
     */
    public Destination updateDestination(Long id, Destination destinationDetails) {
        Destination destination = destinationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Destination not found"));
        destination.setName(destinationDetails.getName());
        destination.setDescription(destinationDetails.getDescription());
        return destinationRepository.save(destination);
    }

    /**
     * Deletes a destination.
     * @param id the ID of the destination to delete.
     */
    public void deleteDestination(Long id) {
        Destination destination = destinationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Destination not found"));
        destinationRepository.delete(destination);
    }
}
