package com.felipe.ms_patient_labcenter.services;

import com.felipe.ms_patient_labcenter.dtos.CreatePatientRequestDTO;
import com.felipe.ms_patient_labcenter.entities.Patient;
import com.felipe.ms_patient_labcenter.exceptions.FieldAlreadyInUseException;
import com.felipe.ms_patient_labcenter.exceptions.ResourceNotFoundException;
import com.felipe.ms_patient_labcenter.repositories.PatientRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@Service
public class PatientService {

    private final PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Transactional
    public Patient save(CreatePatientRequestDTO dto) {
        Optional<Patient> foundedPatient = patientRepository.findByCpf(dto.cpf());
        if(foundedPatient.isPresent())
            throw new FieldAlreadyInUseException("CPF", dto.cpf());

        foundedPatient = patientRepository.findByRg(dto.rg());
        if(foundedPatient.isPresent())
            throw new FieldAlreadyInUseException("RG", dto.rg());

        return patientRepository.save(this.createPatient(dto));
    }

    public Page<Patient> findAll(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("name"));
        return patientRepository.findAll(pageable);
    }

    public Patient findById(UUID id) {
        return patientRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Patient", "ID", id.toString()));
    }

    private Patient createPatient(CreatePatientRequestDTO dto) {
        Patient newPatient = new Patient();
        newPatient.setName(dto.name());
        newPatient.setGender(dto.gender().toUpperCase().charAt(0));
        newPatient.setCpf(dto.cpf());
        newPatient.setRg(dto.rg());
        newPatient.setAge(dto.age());
        newPatient.setPhoneNumber(dto.phoneNumber());
        newPatient.setBirthDate(LocalDate.parse(dto.birthDate()));
        newPatient.setRegisterDate(LocalDate.now());
        return newPatient;
    }
}
