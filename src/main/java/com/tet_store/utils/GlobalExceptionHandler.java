package com.tet_store.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static String BACKEND_ERROR =  "Failed. Backend Error";
    private static String ACCESS_DENIED_ERROR =  "Access Denied - User Not AUthorised";

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<Map<String, Map<String, String>>> handleResponseStatusException(ResponseStatusException ex) {
        // Customizing the response structure
        Map<String, Map<String, String>> response = new HashMap<>();
        Map<String, String> message = new HashMap<>();
        message.put("message", ex.getReason());
        response.put("messages", message);
        return new ResponseEntity<>(response, ex.getStatusCode());
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<StandardJsonResponse> handleRuntimeException(RuntimeException ex) {
        ex.printStackTrace();
        StandardJsonResponse response = new StandardJsonResponse();
        response.setSuccess(false);
        response.setMessage("message",BACKEND_ERROR, response);
        response.setStatus(500);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<StandardJsonResponse> handleResourceNotFound(ResourceNotFoundException ex) {
        StandardJsonResponse response = new StandardJsonResponse();
        response.setSuccess(false);
        response.setMessage("message",ex.getMessage(), response);
        response.setStatus(HttpStatus.OK.value());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }



    public static class ResourceNotFoundException extends RuntimeException {
        public ResourceNotFoundException(String message) {
            super(message);
        }
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<StandardJsonResponse> handleException(Exception ex) {
        ex.printStackTrace();
        StandardJsonResponse response = new StandardJsonResponse();
        response.setSuccess(false);
        response.setMessage("message",BACKEND_ERROR, response);
        response.setStatus(400);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<StandardJsonResponse> handleIllegalArgumentException(IllegalArgumentException ex) {
        ex.printStackTrace();
        StandardJsonResponse response = new StandardJsonResponse();
        response.setSuccess(false);
        response.setMessage("message", BACKEND_ERROR,response);
        response.setStatus(400);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalAccessException.class)
    public ResponseEntity<StandardJsonResponse> handleAccessDeniedException(IllegalAccessException ex) {
        ex.printStackTrace();
        StandardJsonResponse response = new StandardJsonResponse();
        response.setSuccess(false);

        response.setMessage("message", ACCESS_DENIED_ERROR,response);
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }
}
