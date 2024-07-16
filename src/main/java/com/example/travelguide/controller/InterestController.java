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

@RestController
@RequestMapping("/interests")
public class InterestController {

    @Autowired
    private InterestService interestService;

    @GetMapping
    public ResponseEntity<Page<Interest>> getAllInterests(
            @RequestParam int page,
            @RequestParam int size,
            @RequestParam(required = false) String sortBy) {
        PageRequest pageRequest = PageRequest.of(page, size, sortBy == null ? Sort.unsorted() : Sort.by(sortBy));
        Page<Interest> interests = interestService.getAllInterests(pageRequest);
        return ResponseEntity.ok(interests);
    }

    @PostMapping
    public ResponseEntity<Interest> createInterest(@RequestBody Interest interest) {
        Interest createdInterest = interestService.saveInterest(interest);
        return new ResponseEntity<>(createdInterest, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Interest> updateInterest(@PathVariable Long id, @RequestBody Interest interestDetails) {
        Interest updatedInterest = interestService.updateInterest(id, interestDetails);
        return ResponseEntity.ok(updatedInterest);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInterest(@PathVariable Long id) {
        interestService.deleteInterest(id);
        return ResponseEntity.noContent().build();
    }
}
