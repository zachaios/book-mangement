package com.zack.bookserver.config;

public class ResponseUtil {
    public static <T> ResponseResult<T> success(T data) {
        return new ResponseResult<>(200, "Success", data);
    }
 
    public static <T> ResponseErrorResult<T> error(int code, String message) {
        return new ResponseErrorResult<>(code, message, null);
    }
}