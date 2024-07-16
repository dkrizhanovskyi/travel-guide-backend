package com.example.travelguide.model;

import java.io.Serializable;

/**
 * Class representing a JWT request with username and password.
 */
public class JwtRequest implements Serializable {

    private static final long serialVersionUID = 5926468583005150707L;

    private String username;
    private String password;

    /**
     * Default constructor needed for JSON Parsing.
     */
    public JwtRequest() {
    }

    /**
     * Constructor with parameters.
     * @param username the username of the user.
     * @param password the password of the user.
     */
    public JwtRequest(String username, String password) {
        this.setUsername(username);
        this.setPassword(password);
    }

    // Getters and Setters

    /**
     * Gets the username.
     * @return the username.
     */
    public String getUsername() {
        return this.username;
    }

    /**
     * Sets the username.
     * @param username the username to set.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets the password.
     * @return the password.
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * Sets the password.
     * @param password the password to set.
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
