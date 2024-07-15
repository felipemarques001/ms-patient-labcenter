package com.felipe.ms_patient_labcenter.dtos;

import com.felipe.ms_patient_labcenter.validators.cpf.CpfConstraint;
import com.felipe.ms_patient_labcenter.validators.dates.LocalDateConstraint;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

public record CreatePatientRequestDTO (
    @NotBlank(message = "The 'name' field cannot be empty")
    String name,

    // Precisei definir gender como uma String aqui para os validadores poderem funcionar
    @NotNull(message = "The 'gender' field cannot be empty")
    @Length(max = 1, message = "The 'gender' field must has 1 character")
    String gender,

    @NotBlank(message = "The 'cpf' field cannot be empty")
    @CpfConstraint
    String cpf,

    @NotBlank(message = "The 'rg' field cannot be empty")
    @Length(min = 7, max = 20, message = "The 'rg' field must contain between 7 and 20 characters")
    String rg,

    @NotNull(message = "The field 'age' cannot be empty")
    Integer age,

    @NotNull(message = "The 'phoneNumber' field cannot be empty")
    @Length(min = 8, max = 20, message = "The 'phoneNumber' field must contain between 8 and 20 characters")
    String phoneNumber,

    @NotNull(message = "The field 'birthDate' cannot be empty")
    @LocalDateConstraint
    String birthDate
){
}
