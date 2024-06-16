package com.app.e_commerce.Source.exception;

import com.app.e_commerce.Source.constants.ResponseStatus;
import com.app.e_commerce.Source.dto.Response;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.WebUtils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@ControllerAdvice
public class RestResponseEntityExceptionHandler {
    @Autowired
    ObjectMapper objectMapper;

    @ExceptionHandler(value = {RuntimeException.class})
    protected ResponseEntity<Object> handleRuntimeException(RuntimeException ex, WebRequest request) {
        log.debug("RestResponseEntityExceptionHandler.handleRuntimeException...");

        HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.APPLICATION_JSON);

        Response<Object> response = new Response<>(ResponseStatus.ERROR, new Date());
        response.setMessageCode("0001ERR");
        response.setMessage(ex.getMessage());

        log.error(response.getMessageCode(), ex);

        String body = null;
        try {
            body = objectMapper.writeValueAsString(response);
        } catch (JsonProcessingException ex2) {
            body = ex2.getMessage();

            log.error("Generate response got JsonProcessingException...", ex2);
        }

        return handleExceptionInternal(ex, body, header, HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

    @ExceptionHandler(value = {Exception.class})
    protected ResponseEntity<Object> handleExceptionGlobal(Exception ex, WebRequest request) {
        log.debug("RestResponseEntityExceptionHandler.handleException...");

        HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.APPLICATION_JSON);

        Response<Object> response = new Response<>(ResponseStatus.ERROR, new Date());
            response.setMessageCode("0001ERR");
            response.setMessage(ex.getCause().getMessage());
            response.setData(null);
       
        log.error(response.getMessageCode(), ex);

        String body = null;
        try {
            body = objectMapper.writeValueAsString(response);
        } catch (JsonProcessingException ex2) {
            body = ex2.getMessage();

            log.error("Generate response got JsonProcessingException...", ex2);
        }

        return handleExceptionInternal(ex, body, header, HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

    @ExceptionHandler(value = {CannotGetJdbcConnectionException.class})
    protected ResponseEntity<Object> handleExceptionConnectDb(CannotGetJdbcConnectionException ex, WebRequest request) {
        log.debug("DataIntegrityViolationException.handleException...");

        HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.APPLICATION_JSON);

        Response<Object> response = new Response<>(ResponseStatus.ERROR, new Date());
            response.setMessageCode("0001ERR");
            response.setMessage(ex.getCause().getMessage());
            response.setData(null);
       
        log.error(response.getMessageCode(), ex);

        String body = null;
        try {
            body = objectMapper.writeValueAsString(response);
        } catch (JsonProcessingException ex2) {
            body = ex2.getMessage();

            log.error("Generate response got JsonProcessingException...", ex2);
        }

        return handleExceptionInternal(ex, body, header, HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

    @ExceptionHandler(value = {SQLException.class})
    protected ResponseEntity<Object> handleExceptionSQL(SQLException ex, WebRequest request) {
        log.debug("DataIntegrityViolationException.handleException...");

        HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.APPLICATION_JSON);

        Response<Object> response = new Response<>(ResponseStatus.ERROR, new Date());
            response.setMessageCode("0001ERR");
            response.setMessage(ex.getCause().getMessage());
            response.setData(null);
       
        log.error(response.getMessageCode(), ex);

        String body = null;
        try {
            body = objectMapper.writeValueAsString(response);
        } catch (JsonProcessingException ex2) {
            body = ex2.getMessage();

            log.error("Generate response got JsonProcessingException...", ex2);
        }

        return handleExceptionInternal(ex, body, header, HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

    

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex, WebRequest request)
    {
        log.error("errorHandler [MethodArgumentNotValidException] catches an error:",ex);

        HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.APPLICATION_JSON);

        List<String> errors = new ArrayList<String>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.add(error.getField() + ": " + error.getDefaultMessage());
        }
        for (ObjectError error : ex.getBindingResult().getGlobalErrors()) {
            errors.add(error.getObjectName() + ": " + error.getDefaultMessage());
        }

        Response<Object> response = new Response<>(ResponseStatus.ERROR, new Date());
            response.setMessageCode("0001ERR");
            response.setMessage(String.join(", ", errors));
            response.setData(null);

            String body = null;
            try {
                body = objectMapper.writeValueAsString(response);
            } catch (JsonProcessingException ex2) {
                body = ex2.getMessage();
    
                log.error("Generate response got JsonProcessingException...", ex2);
            }
            return handleExceptionInternal(ex, body, header, HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

    @ExceptionHandler(value = {ResponseStatusException.class})
    protected ResponseEntity<Object> handleResponseStatusException(ResponseStatusException ex, WebRequest request) {
        log.debug("RestResponseEntityExceptionHandler.handleResponseStatusException...");

        HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.APPLICATION_JSON);

        Response<Object> response = new Response<>(ResponseStatus.ERROR, new Date());
        response.setMessageCode(null);
        response.setMessage(ex.getReason());

        String body = null;
        try {
            body = objectMapper.writeValueAsString(response);
        } catch (JsonProcessingException ex2) {
            body = ex2.getMessage();

            log.error("Generate response got JsonProcessingException...", ex2);
        }

        return handleExceptionInternal(ex, body, header, ex.getStatusCode(), request);
    }

    @ExceptionHandler(value = {ValidationException.class})
    protected ResponseEntity<Object> handleValidationException(ValidationException ex, WebRequest request) {
        log.debug("RestResponseEntityExceptionHandler.handleValidationException...");

        HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.APPLICATION_JSON);

        Response<Object> response = new Response<>(ResponseStatus.ERROR, new Date());
        response.setMessageCode(ex.getMessageCode());
        response.setMessage(String.join(", ", ex.getVarValues()));

        log.error(response.getMessageCode(), ex);

        String body = null;
        try {
            body = objectMapper.writeValueAsString(response);
        } catch (JsonProcessingException ex2) {
            body = ex2.getMessage();
        }

        return handleExceptionInternal(ex, body, header, HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(value = {BusinessException.class})
    protected ResponseEntity<Object> handleBusinessException(BusinessException ex, WebRequest request) {
        log.debug("RestResponseEntityExceptionHandler.handleBusinessException...");

        HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.APPLICATION_JSON);

        Response<Object> response = new Response<>(ResponseStatus.ERROR, new Date());
        response.setMessageCode(ex.getMessageCode());
        response.setMessage(String.join(", ", ex.getVarValues()));

        log.error(response.getMessageCode(), ex);

        String body = null;
        try {
            body = objectMapper.writeValueAsString(response);
        } catch (JsonProcessingException ex2) {
            body = ex2.getMessage();
        }

        return handleExceptionInternal(ex, body, header, HttpStatus.NOT_ACCEPTABLE, request);
    }

    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, @Nullable Object body, HttpHeaders headers
            , HttpStatusCode status, WebRequest request) {

        if (HttpStatus.INTERNAL_SERVER_ERROR.equals(status)) {
            request.setAttribute(WebUtils.ERROR_EXCEPTION_ATTRIBUTE, ex, WebRequest.SCOPE_REQUEST);
        }
        return new ResponseEntity<>(body, headers, status);
    }

}