package com.example.travelguide.model;

import java.io.Serializable;

/**
 * Class representing a JWT response containing the token.
 */
public class JwtResponse implements Serializable {

    private static final long serialVersionUID = -8091879091924046844L;
    private final String jwttoken;

    /**
     * Constructor with JWT token parameter.
     * @param jwttoken the JWT token.
     */
    public JwtResponse(String jwttoken) {
        this.jwttoken = jwttoken;
    }

    /**
     * Gets the JWT token.
     * @return the JWT token.
     */
    public String getToken() {
        return this.jwttoken;
    }
}
