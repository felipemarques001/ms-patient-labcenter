package com.felipe.ms_patient_labcenter.validators.rg;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = RgValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface RgConstraint {

    String message() default "The RG field must contains between 7 and 9 characters, all of them being numbers";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
