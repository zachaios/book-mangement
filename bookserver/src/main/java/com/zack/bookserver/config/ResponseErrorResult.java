package com.zack.bookserver.config;

import lombok.Data;

@Data
public class ResponseErrorResult<T> {
    private int code;
    private String message;
    private T data;

    public ResponseErrorResult(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

}
