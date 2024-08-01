package com.felipe.ms_patient_labcenter.exceptions;

import lombok.Getter;

@Getter
public class FieldAlreadyInUseException extends RuntimeException {

    private final String fieldError;

    public FieldAlreadyInUseException(String field, String value) {
        super(String.format("Error in the field '%s', the value '%s' is already in use!", field, value));
        this.fieldError = field.toLowerCase();
    }
}