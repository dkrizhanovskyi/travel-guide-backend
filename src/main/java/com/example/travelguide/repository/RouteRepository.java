package com.example.travelguide.repository;

import com.example.travelguide.model.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RouteRepository extends JpaRepository<Route, Long> {

    @Query("SELECT r FROM Route r WHERE r.peopleCount <= :peopleCount AND r.duration <= :duration AND r.interest IN (:interests)")
    List<Route> findByCriteria(int peopleCount, int duration, List<String> interests);
}
