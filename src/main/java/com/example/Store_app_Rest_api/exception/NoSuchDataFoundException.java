package com.example.Store_app_Rest_api.exception;

public class NoSuchDataFoundException extends RuntimeException{
    String msg;

    public NoSuchDataFoundException(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }
}
