package com.example.travelguide.service;

import com.example.travelguide.exception.ResourceNotFoundException;
import com.example.travelguide.model.Route;
import com.example.travelguide.repository.RouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class for managing routes.
 */
@Service
public class RouteService {

    @Autowired
    private RouteRepository routeRepository;

    /**
     * Retrieves all routes.
     * @return a list of all routes.
     */
    public List<Route> getAllRoutes() {
        return routeRepository.findAll();
    }

    /**
     * Retrieves all routes with pagination.
     * @param pageRequest the pagination information.
     * @return a paginated list of routes.
     */
    public Page<Route> getAllRoutes(PageRequest pageRequest) {
        return routeRepository.findAll(pageRequest);
    }

    /**
     * Saves a new route.
     * @param route the route to save.
     * @return the saved route.
     */
    public Route saveRoute(Route route) {
        return routeRepository.save(route);
    }

    /**
     * Updates an existing route.
     * @param id the ID of the route to update.
     * @param routeDetails the updated route details.
     * @return the updated route.
     */
    public Route updateRoute(Long id, Route routeDetails) {
        Route route = routeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Route not found"));
        route.setName(routeDetails.getName());
        route.setDescription(routeDetails.getDescription());
        return routeRepository.save(route);
    }

    /**
     * Deletes a route.
     * @param id the ID of the route to delete.
     */
    public void deleteRoute(Long id) {
        Route route = routeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Route not found"));
        routeRepository.delete(route);
    }
}
