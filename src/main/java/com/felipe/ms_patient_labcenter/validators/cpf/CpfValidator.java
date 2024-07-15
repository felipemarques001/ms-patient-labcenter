package com.felipe.ms_patient_labcenter.validators.cpf;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Pattern;

public class CpfValidator implements ConstraintValidator<CpfConstraint, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        if(value == null) return false;

        String cpfRegex = "([0-9]{3}\\.[0-9]{3}\\.[0-9]{3}-[0-9]{2})";
        Pattern pattern = Pattern.compile(cpfRegex);

        return pattern.matcher(value).matches();
    }
}
