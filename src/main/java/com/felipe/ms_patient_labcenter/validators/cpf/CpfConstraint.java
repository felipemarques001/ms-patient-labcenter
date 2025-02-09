package com.felipe.ms_patient_labcenter.validators.cpf;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = CpfValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface CpfConstraint {

    String message() default "The CPF field must contains 11 characters, all of them being numbers";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
