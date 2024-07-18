package com.felipe.ms_patient_labcenter.validators.gender;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Pattern;

public class GenderValidator implements ConstraintValidator<GenderConstraint, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        if(value == null) return false;

        String cpfRegex = "([mMfF])";
        Pattern pattern = Pattern.compile(cpfRegex);

        return pattern.matcher(value).matches();
    }
}
