package com.dxunited.ruleengine.service.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * RAm Prasad
 */
@ControllerAdvice
@RestController
public class RestException extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {
        String error = "Malformed JSON request";
        return buildResponseEntity(new ErrorMessage(status, status.value(), error, "Provide valid JSON input"));
    }

    private ResponseEntity<Object> buildResponseEntity(ErrorMessage errorMessage) {
        return new ResponseEntity<>(errorMessage, errorMessage.getStatus());
    }

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ErrorMessage> handleAllExceptions(Exception ex, WebRequest request) {
        ErrorMessage errorMessage = new ErrorMessage(HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.value(), "Exception Occured..!", "Bad Request Error Occured..!");
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(SzRuleEngineRestException.class)
    public final ResponseEntity<ErrorMessage> handleUserNotFoundException(SzRuleEngineRestException exception,
                                                                          WebRequest request) {
        ErrorMessage errorMessage
                = new ErrorMessage(exception.getHttpStatus(), exception.getCode(),
                exception.getMessage(), exception.getDeveloperMessage());
        return new ResponseEntity<>(errorMessage, errorMessage.getStatus());
    }

    @ExceptionHandler(SzRuleEngineException.class)
    public final ResponseEntity<ErrorMessage> ruleEngineException(SzRuleEngineException exception,
                                                                  WebRequest request) {
        ErrorMessage errorMessage
                = new ErrorMessage(exception.getStatus(), exception.getCode(),
                exception.getMessage());
        return new ResponseEntity<>(errorMessage, errorMessage.getStatus());
    }
}
