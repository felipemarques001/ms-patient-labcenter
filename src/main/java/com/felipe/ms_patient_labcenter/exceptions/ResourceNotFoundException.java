package com.felipe.ms_patient_labcenter.exceptions;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String resourceName, String fieldName, String fieldValue) {
        super(String.format("%s not found with %s = %s", resourceName, fieldName, fieldValue));
    }
}
