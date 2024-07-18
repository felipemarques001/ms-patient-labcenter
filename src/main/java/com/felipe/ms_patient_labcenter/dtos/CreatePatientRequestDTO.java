package com.felipe.ms_patient_labcenter.dtos;

import com.felipe.ms_patient_labcenter.validators.cpf.CpfConstraint;
import com.felipe.ms_patient_labcenter.validators.dates.LocalDateConstraint;
import com.felipe.ms_patient_labcenter.validators.gender.GenderConstraint;
import com.felipe.ms_patient_labcenter.validators.phoneNumber.PhoneNumberConstraint;
import com.felipe.ms_patient_labcenter.validators.rg.RgConstraint;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.br.CPF;

public record CreatePatientRequestDTO (
    @NotBlank(message = "The 'name' field cannot be empty")
    String name,

    @GenderConstraint
    String gender,

    @CpfConstraint
    @CPF(message = "CPF invalid!")
    String cpf,

    @RgConstraint
    String rg,

    @NotNull(message = "The field 'age' cannot be empty")
    Integer age,

    @PhoneNumberConstraint
    String phoneNumber,

    @LocalDateConstraint
    String birthDate
){
}
