package com.example.RevaDo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<Map<String, Object>> handleApiException(ApiException ex) {
        return ResponseEntity
                .status(ex.getStatus())
                .body(
                        Map.of(
                                "status", ex.getStatus().value(),
                                "message", ex.getMessage(),
                                "error", ex.getStatus().getReasonPhrase()
                        )
                );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleAllExceptions(Exception ex) {

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(
                        Map.of(
                                "status", HttpStatus.INTERNAL_SERVER_ERROR.value(),
                                "error", ex.getClass().getSimpleName(),
                                "message", ex.getMessage()
                        )
                );
    }
}
