package com.kaminsky.marketorders.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

@ControllerAdvice
public class GlobalExceptionHandler {

        @ExceptionHandler(MethodArgumentNotValidException.class)
        public ResponseEntity<String> handleValidationExceptions(MethodArgumentNotValidException ex) {
            return new ResponseEntity<>("Invalid data in the request", HttpStatus.BAD_REQUEST);
        }

        @ExceptionHandler(HttpMessageNotReadableException.class)
        public ResponseEntity<String> handleInvalidJson(HttpMessageNotReadableException ex) {
            return new ResponseEntity<>("Invalid JSON", HttpStatus.BAD_REQUEST);
        }

        @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
        public ResponseEntity<String> handleInvalidMethod(HttpRequestMethodNotSupportedException ex) {
            return new ResponseEntity<>("This request method is not supported", HttpStatus.BAD_REQUEST);
        }

        @ExceptionHandler(JsonException.class)
        public ResponseEntity<String> handleInvalidMethod(JsonException ex) {
            return new ResponseEntity<>("JSON issue", HttpStatus.BAD_REQUEST);
        }

        @ExceptionHandler(ResponseStatusException.class)
        public ResponseEntity<String> handleInvalidMethod(ResponseStatusException ex) {
            return new ResponseEntity<>("Object not found", HttpStatus.NOT_FOUND);
        }
    }
