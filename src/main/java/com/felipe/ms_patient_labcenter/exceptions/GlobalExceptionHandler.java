package com.felipe.ms_patient_labcenter.exceptions;

import com.felipe.ms_patient_labcenter.dtos.ApiGlobalResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(FieldAlreadyInUseException.class)
    protected ResponseEntity<ApiGlobalResponseDTO> handleFieldAlreadyInUseException(RuntimeException ex) {
        var response = new ApiGlobalResponseDTO(ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
}
