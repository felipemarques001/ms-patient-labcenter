package com.felipe.ms_patient_labcenter.validators.rg;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Pattern;

public class RgValidator implements ConstraintValidator<RgConstraint, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        if(value == null) return false;

        String cpfRegex = "([0-9]{7,9})";
        Pattern pattern = Pattern.compile(cpfRegex);

        return pattern.matcher(value).matches();
    }
}
