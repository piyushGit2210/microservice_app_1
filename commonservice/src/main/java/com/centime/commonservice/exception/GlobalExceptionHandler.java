package com.centime.commonservice.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.context.support.DefaultMessageSourceResolvable;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleInvalidJson(MethodArgumentNotValidException ex) {
        logger.error("Invalid JSON: {}", ex.getBindingResult().getFieldErrors());

        // Collect error messages
        String errorMessages = ex.getBindingResult().getFieldErrors().stream()
                .map(fieldError -> fieldError.getField() + ": " + fieldError.getDefaultMessage())
                .collect(Collectors.joining(", "));

        // Prepare structured response
        Map<String, Object> response = new HashMap<>();
        response.put("status", "error");
        response.put("message", "Invalid JSON");
        response.put("errors", errorMessages);

        // Return structured JSON response
        return ResponseEntity.badRequest().body(response.toString());
    }

}
