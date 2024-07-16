package com.example.travelguide.security;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;

/**
 * Implementation of AuthenticationEntryPoint that rejects all unauthorized requests with an HTTP 401 error.
 */
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint, Serializable {

    private static final long serialVersionUID = -7858869558953243875L;

    /**
     * Responds with an HTTP 401 Unauthorized error.
     * @param request The HttpServletRequest being processed.
     * @param response The HttpServletResponse being created.
     * @param authException The exception that caused the invocation.
     * @throws IOException if an input or output exception occurred
     */
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException {
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
    }
}
