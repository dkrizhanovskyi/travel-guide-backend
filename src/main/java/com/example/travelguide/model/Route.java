package com.example.travelguide.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Entity class representing a travel route.
 */
@Entity
public class Route {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;

    /**
     * Default constructor.
     */
    public Route() {
    }

    /**
     * Constructor with parameters.
     * @param id the ID of the route.
     * @param name the name of the route.
     * @param description the description of the route.
     */
    public Route(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    // Getters and Setters

    /**
     * Gets the ID of the route.
     * @return the ID of the route.
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the ID of the route.
     * @param id the ID to set.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the name of the route.
     * @return the name of the route.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the route.
     * @param name the name to set.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the description of the route.
     * @return the description of the route.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the route.
     * @param description the description to set.
     */
    public void setDescription(String description) {
        this.description = description;
    }
}
