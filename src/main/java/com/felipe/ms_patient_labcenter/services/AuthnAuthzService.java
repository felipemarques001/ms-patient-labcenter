package com.felipe.ms_patient_labcenter.services;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class AuthnAuthzService {

    public final static String ADMIN_ROLE = "ADMIN";
    public final static String USER_ROLE = "USER";
    private final String URL = "http://localhost:8080/authorization";
    private final HttpServletRequest request;

    public AuthnAuthzService(HttpServletRequest request) {
        this.request = request;
    }

    public Boolean verifyUserRole(String role) {
        RequestEntity<Void> requestEntity = buildRequest(role);

        if(requestEntity == null) return false;

        try {
            var restTemplate = new RestTemplate();
            HttpStatusCode statusCode = restTemplate
                    .exchange(requestEntity, Void.class)
                    .getStatusCode();

            return statusCode.value() == 200;
        } catch (HttpClientErrorException ex) {
            // Trata erros do tipo 4xx
            return false;
        } catch (HttpServerErrorException ex) {
            // Trata erros do tipo 5xx
            return false;
        }
    }

    private RequestEntity<Void> buildRequest(String role) {
        var token = request.getHeader("Authorization");
        if(token == null) return null;

        var headers = new HttpHeaders();
        headers.set("Authorization", token);

        return role.equals(ADMIN_ROLE)
                ? RequestEntity.get(URL + "/admin").headers(headers).build()
                : RequestEntity.get(URL + "/user").headers(headers).build();
    }
}
