package com.felipe.ms_patient_labcenter.repositories;

import com.felipe.ms_patient_labcenter.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PatientRepository extends JpaRepository<Patient, UUID> {
}
