package com.felipe.ms_patient_labcenter.validators.dates;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Pattern;

public class LocalDateValidator implements ConstraintValidator<LocalDateConstraint, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        if(value == null) return false;

        String localDateRegex = "([0-9]{4}-[0-9]{2}-[0-9]{2})";
        Pattern pattern = Pattern.compile(localDateRegex);

        return pattern.matcher(value).matches();
    }
}
