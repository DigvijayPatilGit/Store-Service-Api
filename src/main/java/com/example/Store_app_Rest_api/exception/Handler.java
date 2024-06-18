package com.example.Store_app_Rest_api.exception;

import com.example.Store_app_Rest_api.dto.StoreCustomeException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class Handler {

    @ExceptionHandler(EntityNotFoundException.class)
    protected ResponseEntity<?> handlerEntityNotFound(EntityNotFoundException ex) {
        StoreCustomeException customException = new StoreCustomeException(
                HttpStatus.NOT_FOUND,
                ex.getMessage(),
                ex.getLocalizedMessage());
        return new ResponseEntity<>(customException,HttpStatus.NOT_FOUND);
    }

}
