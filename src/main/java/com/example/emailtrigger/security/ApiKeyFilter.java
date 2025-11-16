package com.example.emailtrigger.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class ApiKeyFilter extends HttpFilter {

    @Value("${app.api-key:dev-key}")
    private String apiKey;

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        String path = req.getRequestURI();

        // Only protect send endpoint
        if (path.equals("/api/v1/email/send")) {
            String key = req.getHeader("X-API-KEY");



            if (key == null || !key.equals(apiKey)) {
                res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                res.setContentType("application/json");
                res.getWriter().write("{\"error\":\"Unauthorized: Invalid or missing API key\"}");
                return;
            }
        }

        chain.doFilter(req, res);
    }
}
