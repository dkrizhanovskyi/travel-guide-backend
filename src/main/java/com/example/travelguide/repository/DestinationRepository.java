package com.example.travelguide.repository;

import com.example.travelguide.model.Destination;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DestinationRepository extends JpaRepository<Destination, Long> {
    Page<Destination> findAll(Pageable pageable);
    Page<Destination> findByNameContaining(String name, Pageable pageable);
}
