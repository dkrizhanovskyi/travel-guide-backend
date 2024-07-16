package com.example.travelguide.repository;

import com.example.travelguide.model.Route;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for accessing Route data.
 */
@Repository
public interface RouteRepository extends JpaRepository<Route, Long> {

    /**
     * Finds all routes with pagination.
     * @param pageable the pagination information.
     * @return a paginated list of routes.
     */
    Page<Route> findAll(Pageable pageable);
}
