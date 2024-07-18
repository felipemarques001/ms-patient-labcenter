package com.felipe.ms_patient_labcenter.dtos;

import com.felipe.ms_patient_labcenter.entities.Patient;
import org.springframework.data.domain.Page;

import java.util.List;

public record PatientPaginationDTO(
        List<Patient> content,
        Integer pageNumber,
        Integer pageSize,
        Long totalElements,
        Integer totalPages,
        Boolean lastPage
) {

    public static PatientPaginationDTO create(Page<Patient> patientPage) {
        return new PatientPaginationDTO(
            patientPage.getContent(),
            patientPage.getNumber(),
            patientPage.getSize(),
            patientPage.getTotalElements(),
            patientPage.getTotalPages(),
            patientPage.isLast()
        );
    }
}
