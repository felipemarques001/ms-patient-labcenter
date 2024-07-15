package com.felipe.ms_patient_labcenter.validators.dates;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = LocalDateValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface LocalDateConstraint {

    String message() default "The date is not at 'yyyy-MM-dd' pattern";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
