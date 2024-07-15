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

    final String URL = "http://localhost:8080/authorization/admin";

    private final HttpServletRequest request;

    public AuthnAuthzService(HttpServletRequest request) {
        this.request = request;
    }

    public Boolean verifyIfUserHasAdminRole() {
        var token = request.getHeader("Authorization");
        if(token == null) return false;

        var restTemplate = new RestTemplate();
        var headers = new HttpHeaders();
        headers.set("Authorization", token);

        RequestEntity<Void> requestEntity = RequestEntity.get(URL).headers(headers).build();

        try {
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
}
