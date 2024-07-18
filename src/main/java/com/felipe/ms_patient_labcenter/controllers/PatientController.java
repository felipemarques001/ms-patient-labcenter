package com.felipe.ms_patient_labcenter.controllers;

import com.felipe.ms_patient_labcenter.dtos.CreatePatientRequestDTO;
import com.felipe.ms_patient_labcenter.dtos.ApiGlobalResponseDTO;
import com.felipe.ms_patient_labcenter.dtos.PatientPaginationDTO;
import com.felipe.ms_patient_labcenter.entities.Patient;
import com.felipe.ms_patient_labcenter.services.AuthnAuthzService;
import com.felipe.ms_patient_labcenter.services.PatientService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/patients")
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
        Boolean userHasAdminRole = authnAuthzService.verifyUserRole(AuthnAuthzService.ADMIN_ROLE);

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

    @GetMapping
    public ResponseEntity<ApiGlobalResponseDTO> getPatients(
            @RequestParam(value = "pageNumber", defaultValue = "0", required = false) int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize
    ) {
        Boolean userHasUserRole = authnAuthzService.verifyUserRole(AuthnAuthzService.USER_ROLE);

        if(!userHasUserRole) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(new ApiGlobalResponseDTO("User does not have access to see all the patients!"));
        }

        Page<Patient> responseBody = service.findAll(pageNumber, pageSize);
        var response = new ApiGlobalResponseDTO(PatientPaginationDTO.create(responseBody));
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }

    @GetMapping("{id}")
    public ResponseEntity<ApiGlobalResponseDTO> getPatientById(@PathVariable(name = "id") UUID id) {
        Boolean userHasUserRole = authnAuthzService.verifyUserRole(AuthnAuthzService.USER_ROLE);

        if(!userHasUserRole) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(new ApiGlobalResponseDTO("User does not have access to see the patient!"));
        }

        Patient responseBody = service.findById(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ApiGlobalResponseDTO(responseBody));
    }
}
