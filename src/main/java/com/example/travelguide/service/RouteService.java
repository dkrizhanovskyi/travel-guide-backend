package com.example.travelguide.service;

import com.example.travelguide.exception.ResourceNotFoundException;
import com.example.travelguide.model.Route;
import com.example.travelguide.repository.RouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RouteService {

    @Autowired
    private RouteRepository routeRepository;

    public List<Route> getAllRoutes() {
        return routeRepository.findAll();
    }

    public Page<Route> getAllRoutes(PageRequest pageRequest) {
        return routeRepository.findAll(pageRequest);
    }

    public Route saveRoute(Route route) {
        return routeRepository.save(route);
    }

    public Route updateRoute(Long id, Route routeDetails) {
        Route route = routeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Route not found"));
        route.setName(routeDetails.getName());
        route.setDescription(routeDetails.getDescription());
        return routeRepository.save(route);
    }

    public void deleteRoute(Long id) {
        Route route = routeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Route not found"));
        routeRepository.delete(route);
    }
}
