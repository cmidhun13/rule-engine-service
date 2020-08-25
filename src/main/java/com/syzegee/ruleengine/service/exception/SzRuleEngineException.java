package com.syzegee.ruleengine.service.exception;

import org.springframework.http.HttpStatus;

/**
 * Ram Prasad
 */
public class SzRuleEngineException extends RuntimeException {
    private HttpStatus status;
    private int code = -1;
    private String message;



    public SzRuleEngineException() {
    }

    public SzRuleEngineException(Exception exception) {
        super(exception);
    }

    public SzRuleEngineException(HttpStatus status,int code, String message) {
        this.code = code;
        this.status = status;
        this.message=message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
