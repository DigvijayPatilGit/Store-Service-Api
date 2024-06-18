package com.example.Store_app_Rest_api.dto;

import org.springframework.http.HttpStatus;

public class ResponseStore {
    public String message;
    public int code;
    public HttpStatus httpStatus;

    public ResponseStore(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.code = httpStatus.value();
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }
}
