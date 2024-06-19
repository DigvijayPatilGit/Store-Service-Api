package com.example.Store_app_Rest_api.exception;

public class IdNotFoundException extends RuntimeException{
    String msg;

    public IdNotFoundException(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }
}
