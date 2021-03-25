package com.dxunited.ruleengine.service.exception;


import org.springframework.http.HttpStatus;

/**
 * RAm Prasad
 */
public class SzRuleEngineRestException  extends RuntimeException  {

    private HttpStatus status;
    private int code;
    private String message;
    private Object developerMessage;

    public SzRuleEngineRestException(Exception exception) {
        super(exception);
    }

    public SzRuleEngineRestException(HttpStatus httpStatus, int code, String message, Object developerMessage) {
        this.status = httpStatus;
        this.developerMessage = developerMessage;
        this.code = code;
        this.message = message;
    }

    public HttpStatus getHttpStatus() {
        return status;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.status = httpStatus;
    }

    public Object getDeveloperMessage() {
        return developerMessage;
    }

    public void setDeveloperMessage(Object developerMessage) {
        this.developerMessage = developerMessage;
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
