package com.API.listaTareas.exception;

import com.API.listaTareas.exception.modeloError.ErrorJson;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class AccountsExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(MensajeError.class)
    protected ResponseEntity<Object> handleUsuarioNotFoundException(MensajeError e, WebRequest request) {
        ErrorJson error = new ErrorJson(HttpStatus.NOT_FOUND.value(), e.getMessage());
        return handleExceptionInternal(e, error, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllExceptions(Exception e, WebRequest request) {
        ErrorJson error = new ErrorJson(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
        return handleExceptionInternal(e, error, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }
}

