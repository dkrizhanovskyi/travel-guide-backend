package com.example.travelguide.controller;

import com.example.travelguide.model.Interest;
import com.example.travelguide.service.InterestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for managing interests.
 */
@RestController
@RequestMapping("/interests")
public class InterestController {

    @Autowired
    private InterestService interestService;

    /**
     * Get all interests.
     * 
     * @return A ResponseEntity containing a list of all interests.
     */
    @GetMapping
    public ResponseEntity<List<Interest>> getAllInterests() {
        List<Interest> interests = interestService.getAllInterests();
        return ResponseEntity.ok(interests);
    }

    /**
     * Create a new interest.
     * 
     * @param interest The interest to create.
     * @return A ResponseEntity containing the created interest and HTTP status CREATED.
     */
    @PostMapping
    public ResponseEntity<Interest> createInterest(@RequestBody Interest interest) {
        Interest createdInterest = interestService.saveInterest(interest);
        return new ResponseEntity<>(createdInterest, HttpStatus.CREATED);
    }

    /**
     * Update an existing interest.
     * 
     * @param id The ID of the interest to update.
     * @param interestDetails The updated interest details.
     * @return A ResponseEntity containing the updated interest.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Interest> updateInterest(@PathVariable Long id, @RequestBody Interest interestDetails) {
        Interest updatedInterest = interestService.updateInterest(id, interestDetails);
        return ResponseEntity.ok(updatedInterest);
    }

    /**
     * Delete an interest.
     * 
     * @param id The ID of the interest to delete.
     * @return A ResponseEntity with HTTP status NO_CONTENT.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInterest(@PathVariable Long id) {
        interestService.deleteInterest(id);
        return ResponseEntity.noContent().build();
    }
}
