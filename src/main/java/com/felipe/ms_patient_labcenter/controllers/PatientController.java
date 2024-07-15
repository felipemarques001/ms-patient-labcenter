package com.felipe.ms_patient_labcenter.controllers;

import com.felipe.ms_patient_labcenter.dtos.CreatePatientRequestDTO;
import com.felipe.ms_patient_labcenter.dtos.ApiGlobalResponseDTO;
import com.felipe.ms_patient_labcenter.entities.Patient;
import com.felipe.ms_patient_labcenter.services.AuthnAuthzService;
import com.felipe.ms_patient_labcenter.services.PatientService;
import jakarta.validation.Valid;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/patients")
public class PatientController {

    private final PatientService service;
    private final AuthnAuthzService authnAuthzService;

    public PatientController(PatientService service,
                             AuthnAuthzService authnAuthzService) {
        this.service = service;
        this.authnAuthzService = authnAuthzService;
    }

    @PostMapping
    public ResponseEntity<ApiGlobalResponseDTO> save(@RequestBody @Valid CreatePatientRequestDTO requestBody) {
        Boolean userHasAdminRole = authnAuthzService.verifyIfUserHasAdminRole();

        if(!userHasAdminRole) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(new ApiGlobalResponseDTO("User does not have access to create a patient!"));
        }

        Patient responseBody = service.save(requestBody);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ApiGlobalResponseDTO(responseBody));
    }
}
