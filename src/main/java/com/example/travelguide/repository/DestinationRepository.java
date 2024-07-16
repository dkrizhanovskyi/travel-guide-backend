package com.example.travelguide.repository;

import com.example.travelguide.model.Destination;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for accessing Destination data.
 */
@Repository
public interface DestinationRepository extends JpaRepository<Destination, Long> {
    
    /**
     * Finds all destinations with pagination.
     * @param pageable the pagination information.
     * @return a paginated list of destinations.
     */
    Page<Destination> findAll(Pageable pageable);
    
    /**
     * Finds destinations by name containing the specified string with pagination.
     * @param name the name to search for.
     * @param pageable the pagination information.
     * @return a paginated list of destinations matching the search criteria.
     */
    Page<Destination> findByNameContaining(String name, Pageable pageable);
}
