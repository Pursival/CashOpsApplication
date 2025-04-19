package com.fib.cashops.config;

import com.fib.cashops.security.ApiKeyValidator;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class ApiKeyFilter implements Filter {

    private final ApiKeyValidator apiKeyValidator;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpReq = (HttpServletRequest) request;
        HttpServletResponse httpResp = (HttpServletResponse) response;

        String apiKey = httpReq.getHeader("FIB-X-AUTH");

        if (apiKey == null || !apiKeyValidator.isValid(apiKey)) {
            httpResp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            httpResp.getWriter().write("Invalid or missing API Key");
            return;
        }

        chain.doFilter(request, response);
    }
}