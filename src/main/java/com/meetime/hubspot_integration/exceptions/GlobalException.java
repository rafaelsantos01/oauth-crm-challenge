package com.meetime.hubspot_integration.exceptions;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.FeignException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.OffsetDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalException {


    @ExceptionHandler(FeignException.Unauthorized.class)
    public ResponseEntity<Map<String, Object>> handleFeignUnauthorized(FeignException.Unauthorized ex) {
        Map<String, Object> body = new HashMap<>();
        body.put("status", HttpStatus.UNAUTHORIZED.value());
        body.put("error", "Unauthorized");
        body.put("message", "Erro de autenticação ao chamar API externa.");
        body.put("details", ex.contentUTF8());
        body.put("timestamp", OffsetDateTime.now());

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(body);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, Object> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        Map<String, Object> body = new HashMap<>();
        body.put("status", HttpStatus.BAD_REQUEST.value());
        body.put("error", "Bad Request");
        body.put("message", "Erro de validação");
        body.put("details", errors);
        body.put("timestamp", OffsetDateTime.now());

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<Map<String, Object>> handleMethodNotSupported(HttpRequestMethodNotSupportedException ex) {
        Map<String, Object> body = new HashMap<>();
        body.put("status", HttpStatus.METHOD_NOT_ALLOWED.value());
        body.put("error", "Method Not Allowed");
        body.put("message", "Método HTTP não permitido.");
        body.put("details", ex.getMessage());
        body.put("timestamp", OffsetDateTime.now());

        return new ResponseEntity<>(body, HttpStatus.METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleAll(Exception ex) {
        Map<String, Object> body = new HashMap<>();
        body.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
        body.put("error", "Internal Server Error");
        body.put("message", "Ocorreu um erro inesperado.");
        body.put("details", ex.getMessage());
        body.put("timestamp", OffsetDateTime.now());

        return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(AuthorizationDeniedException.class)
    public ResponseEntity<Map<String, Object>> handleAuthorization(Exception ex) {
        Map<String, Object> body = new HashMap<>();
        body.put("status", HttpStatus.UNAUTHORIZED.value());
        body.put("error", "Unauthorized");
        body.put("message", "Acesso não autorizado.");
        body.put("details", ex.getMessage());
        body.put("timestamp", OffsetDateTime.now());

        return new ResponseEntity<>(body, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(FeignException.Conflict.class)
    public ResponseEntity<Map<String, Object>> handleFeignConflict(FeignException.Conflict ex) {
        Map<String, Object> body = new HashMap<>();
        body.put("status", HttpStatus.CONFLICT.value());
        body.put("error", "Conflict");

        String message = "Ocorreu um erro inesperado.";
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(ex.contentUTF8());

            if (root.isArray() && !root.isEmpty()) {
                message = root.get(0).get("message").asText();
            } else if (root.has("message")) {
                message = root.get("message").asText();
            }

        } catch (Exception e) {
            message = "Erro ao processar mensagem de erro da API externa.";
        }

        body.put("message", message);
        body.put("details", ex.contentUTF8());
        body.put("timestamp", OffsetDateTime.now());

        return ResponseEntity.status(HttpStatus.CONFLICT).body(body);
    }

}
