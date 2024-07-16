package com.example.travelguide.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Entity class representing a travel destination.
 */
@Entity
public class Destination {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private String additionalInfo;

    /**
     * Default constructor.
     */
    public Destination() {
    }

    /**
     * Constructor with three parameters.
     * @param id the ID of the destination.
     * @param name the name of the destination.
     * @param description the description of the destination.
     */
    public Destination(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    /**
     * Constructor with four parameters.
     * @param id the ID of the destination.
     * @param name the name of the destination.
     * @param description the description of the destination.
     * @param additionalInfo additional information about the destination.
     */
    public Destination(Long id, String name, String description, String additionalInfo) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.additionalInfo = additionalInfo;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }
}
