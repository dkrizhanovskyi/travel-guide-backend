package com.example.travelguide.controller;

import com.example.travelguide.model.Route;
import com.example.travelguide.repository.RouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/routes")
public class RouteController {

    @Autowired
    private RouteRepository routeRepository;

    @GetMapping("/search")
    public List<Route> searchRoutes(
            @RequestParam int peopleCount,
            @RequestParam int duration,
            @RequestParam List<String> interests) {
        return routeRepository.findByCriteria(peopleCount, duration, interests);
    }
}
