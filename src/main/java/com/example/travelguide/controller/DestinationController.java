package com.example.travelguide.controller;

import com.example.travelguide.exception.ResourceNotFoundException;
import com.example.travelguide.model.Destination;
import com.example.travelguide.service.DestinationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/destinations")
public class DestinationController {

    @Autowired
    private DestinationService destinationService;

    /**
     * Get all destinations.
     * @return List of all destinations.
     */
    @GetMapping
    public List<Destination> getAllDestinations() {
        return destinationService.getAllDestinations();
    }

    /**
     * Create a new destination.
     * @param destination The destination to create.
     * @return The created destination.
     */
    @PostMapping
    public ResponseEntity<Destination> createDestination(@RequestBody Destination destination) {
        Destination createdDestination = destinationService.saveDestination(destination);
        return new ResponseEntity<>(createdDestination, HttpStatus.CREATED);
    }

    /**
     * Update an existing destination.
     * @param id The ID of the destination to update.
     * @param destinationDetails The updated destination details.
     * @return The updated destination, or a 404 error if not found.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Destination> updateDestination(@PathVariable Long id, @RequestBody Destination destinationDetails) {
        try {
            Destination updatedDestination = destinationService.updateDestination(id, destinationDetails);
            return ResponseEntity.ok(updatedDestination);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    /**
     * Delete a destination.
     * @param id The ID of the destination to delete.
     * @return A 204 status if successful, or a 404 error if not found.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDestination(@PathVariable Long id) {
        try {
            destinationService.deleteDestination(id);
            return ResponseEntity.noContent().build();
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
