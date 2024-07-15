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

    String message() default "The CPF is not at 'xxx.xxx.xxx-xx' pattern";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
