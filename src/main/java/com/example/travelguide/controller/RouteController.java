package com.example.travelguide.controller;

import com.example.travelguide.model.Route;
import com.example.travelguide.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for managing routes.
 */
@RestController
@RequestMapping("/routes")
public class RouteController {

    @Autowired
    private RouteService routeService;

    /**
     * Get all routes.
     *
     * @return A ResponseEntity containing a list of all routes.
     */
    @GetMapping
    public ResponseEntity<List<Route>> getAllRoutes() {
        List<Route> routes = routeService.getAllRoutes();
        return ResponseEntity.ok(routes);
    }

    /**
     * Create a new route.
     *
     * @param route The route to create.
     * @return A ResponseEntity containing the created route and HTTP status CREATED.
     */
    @PostMapping
    public ResponseEntity<Route> createRoute(@RequestBody Route route) {
        Route createdRoute = routeService.saveRoute(route);
        return new ResponseEntity<>(createdRoute, HttpStatus.CREATED);
    }

    /**
     * Update an existing route.
     *
     * @param id The ID of the route to update.
     * @param routeDetails The updated route details.
     * @return A ResponseEntity containing the updated route.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Route> updateRoute(@PathVariable Long id, @RequestBody Route routeDetails) {
        Route updatedRoute = routeService.updateRoute(id, routeDetails);
        return ResponseEntity.ok(updatedRoute);
    }

    /**
     * Delete a route.
     *
     * @param id The ID of the route to delete.
     * @return A ResponseEntity with HTTP status NO_CONTENT.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRoute(@PathVariable Long id) {
        routeService.deleteRoute(id);
        return ResponseEntity.noContent().build();
    }
}
