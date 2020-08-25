package com.syzegee.ruleengine.service.exception;

import org.springframework.http.HttpStatus;

/**
 * Ram Prasad
 */
public class ErrorMessage {

    private HttpStatus status;
    private int code;
    private String message;
    private Object developerMessage;

    public ErrorMessage() {
    }

    public ErrorMessage(HttpStatus status, int code, String message, Object developerMessage) {

        super();
        this.status = status;
        this.code = code;
        this.message = message;
        this.developerMessage = developerMessage;
    }

    public ErrorMessage(HttpStatus status, int code, String message) {
        super();
        this.status = status;
        this.code = code;
        this.message = message;
    }

    public ErrorMessage(HttpStatus status, int code) {
        super();
        this.status = status;
        this.code = code;
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

    public Object getDeveloperMessage() {
        return developerMessage;
    }

    public void setDeveloperMessage(Object developerMessage) {
        this.developerMessage = developerMessage;
    }

}
