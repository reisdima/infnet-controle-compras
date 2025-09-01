package br.edu.infnet.caiovincenzo.controller.exception;

import br.edu.infnet.caiovincenzo.model.domain.exceptions.EntidadeInvalidaException;
import br.edu.infnet.caiovincenzo.model.domain.exceptions.EntidadeNaoEncontradaException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EntidadeInvalidaException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(EntidadeInvalidaException ex) {
        Map<String, String> errors = new HashMap<>();

        errors.put("Datahora", LocalDateTime.now().toString());
        errors.put("Status", HttpStatus.BAD_REQUEST.toString());
        errors.put("Mensagem", ex.getMessage());

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EntidadeNaoEncontradaException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(EntidadeNaoEncontradaException ex) {
        Map<String, String> errors = new HashMap<>();

        errors.put("Datahora", LocalDateTime.now().toString());
        errors.put("Status", HttpStatus.NOT_FOUND.toString());
        errors.put("Mensagem", ex.getMessage());

        return new ResponseEntity<>(errors, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(IllegalArgumentException ex) {
        Map<String, String> errors = new HashMap<>();

        errors.put("Datahora", LocalDateTime.now().toString());
        errors.put("Status", HttpStatus.BAD_REQUEST.toString());
        errors.put("Mensagem", ex.getMessage());

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(Exception ex) {
        Map<String, String> errors = new HashMap<>();

        errors.put("Datahora", LocalDateTime.now().toString());
        errors.put("Status", HttpStatus.INTERNAL_SERVER_ERROR.toString());
        errors.put("Mensagem", ex.getMessage());

        return new ResponseEntity<>(errors, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}