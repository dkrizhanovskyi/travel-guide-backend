package com.example.travelguide.service;

import com.example.travelguide.exception.ResourceNotFoundException;
import com.example.travelguide.model.Interest;
import com.example.travelguide.repository.InterestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class for managing interests.
 */
@Service
public class InterestService {

    @Autowired
    private InterestRepository interestRepository;

    /**
     * Retrieves all interests.
     * @return a list of all interests.
     */
    public List<Interest> getAllInterests() {
        return interestRepository.findAll();
    }

    /**
     * Retrieves all interests with pagination.
     * @param pageRequest the pagination information.
     * @return a paginated list of interests.
     */
    public Page<Interest> getAllInterests(PageRequest pageRequest) {
        return interestRepository.findAll(pageRequest);
    }

    /**
     * Saves a new interest.
     * @param interest the interest to save.
     * @return the saved interest.
     */
    public Interest saveInterest(Interest interest) {
        return interestRepository.save(interest);
    }

    /**
     * Updates an existing interest.
     * @param id the ID of the interest to update.
     * @param interestDetails the updated interest details.
     * @return the updated interest.
     */
    public Interest updateInterest(Long id, Interest interestDetails) {
        Interest interest = interestRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Interest not found"));
        interest.setName(interestDetails.getName());
        interest.setDescription(interestDetails.getDescription());
        return interestRepository.save(interest);
    }

    /**
     * Deletes an interest.
     * @param id the ID of the interest to delete.
     */
    public void deleteInterest(Long id) {
        Interest interest = interestRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Interest not found"));
        interestRepository.delete(interest);
    }
}
