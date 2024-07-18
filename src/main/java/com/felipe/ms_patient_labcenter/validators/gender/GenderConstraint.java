package com.felipe.ms_patient_labcenter.validators.gender;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = GenderValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface GenderConstraint {

    String message() default "The gender field must be one of the following values: 'm','M','f','F'";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
