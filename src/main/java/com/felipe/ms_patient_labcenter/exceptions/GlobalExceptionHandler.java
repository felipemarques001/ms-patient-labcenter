package com.felipe.ms_patient_labcenter.exceptions;

import com.felipe.ms_patient_labcenter.dtos.ApiGlobalResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(FieldAlreadyInUseException.class)
    protected ResponseEntity<ApiGlobalResponseDTO> handleFieldAlreadyInUseException(FieldAlreadyInUseException ex) {
        var error = new HashMap<String, String>();
        error.put(ex.getFieldError(), ex.getMessage());
        var response = new ApiGlobalResponseDTO(error);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<ApiGlobalResponseDTO> handleMethodArgumentNotValidException
            (MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldNamme = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            errors.put(fieldNamme, message);
        });
        var response = new ApiGlobalResponseDTO(errors);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
}
