package com.example.travelguide.controller;

import com.example.travelguide.model.Interest;
import com.example.travelguide.repository.InterestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/interests")
@CrossOrigin(origins = "http://localhost:3000")
public class InterestController {

    @Autowired
    private InterestRepository interestRepository;

    @GetMapping
    public List<Interest> getAllInterests() {
        return interestRepository.findAll();
    }

    @PostMapping
    public Interest createInterest(@RequestBody Interest interest) {
        return interestRepository.save(interest);
    }
}
