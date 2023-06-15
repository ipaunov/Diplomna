package com.diploma.ivan.model.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomSuccessHandler implements AuthenticationSuccessHandler {

    private final String defaultUrl;

    public CustomSuccessHandler(String defaultUrl) {
        this.defaultUrl = defaultUrl;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
       if (request.getRequestURI().contains("user/v1/deployments")) {
           response.sendRedirect(request.getRequestURI());
           return;
       }

        response.sendRedirect(defaultUrl);
    }
}
