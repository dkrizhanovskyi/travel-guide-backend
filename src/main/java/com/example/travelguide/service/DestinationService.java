package com.example.travelguide.service;

import com.example.travelguide.exception.ResourceNotFoundException;
import com.example.travelguide.model.Destination;
import com.example.travelguide.repository.DestinationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DestinationService {

    @Autowired
    private DestinationRepository destinationRepository;

    public List<Destination> getAllDestinations() {
        return destinationRepository.findAll();
    }

    public Page<Destination> getAllDestinations(PageRequest pageRequest) {
        return destinationRepository.findAll(pageRequest);
    }

    public Page<Destination> getDestinationsByName(String name, PageRequest pageRequest) {
        return destinationRepository.findByNameContaining(name, pageRequest);
    }

    public Destination saveDestination(Destination destination) {
        return destinationRepository.save(destination);
    }

    public Destination updateDestination(Long id, Destination destinationDetails) {
        Destination destination = destinationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Destination not found"));
        destination.setName(destinationDetails.getName());
        destination.setDescription(destinationDetails.getDescription());
        return destinationRepository.save(destination);
    }

    public void deleteDestination(Long id) {
        Destination destination = destinationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Destination not found"));
        destinationRepository.delete(destination);
    }
}
