package com.example.travelguide.repository;

import com.example.travelguide.model.Interest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for accessing Interest data.
 */
@Repository
public interface InterestRepository extends JpaRepository<Interest, Long> {

    /**
     * Finds all interests with pagination.
     * @param pageable the pagination information.
     * @return a paginated list of interests.
     */
    Page<Interest> findAll(Pageable pageable);
}
