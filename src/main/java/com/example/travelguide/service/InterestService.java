package com.example.travelguide.service;

import com.example.travelguide.exception.ResourceNotFoundException;
import com.example.travelguide.model.Interest;
import com.example.travelguide.repository.InterestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InterestService {

    @Autowired
    private InterestRepository interestRepository;

    public List<Interest> getAllInterests() {
        return interestRepository.findAll();
    }

    public Page<Interest> getAllInterests(PageRequest pageRequest) {
        return interestRepository.findAll(pageRequest);
    }

    public Interest saveInterest(Interest interest) {
        return interestRepository.save(interest);
    }

    public Interest updateInterest(Long id, Interest interestDetails) {
        Interest interest = interestRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Interest not found"));
        interest.setName(interestDetails.getName());
        interest.setDescription(interestDetails.getDescription());
        return interestRepository.save(interest);
    }

    public void deleteInterest(Long id) {
        Interest interest = interestRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Interest not found"));
        interestRepository.delete(interest);
    }
}
