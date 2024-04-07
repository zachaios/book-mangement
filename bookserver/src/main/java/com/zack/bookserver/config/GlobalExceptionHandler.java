package com.zack.bookserver.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
//@RestControllerAdvice
//@ControllerAdvice
public class GlobalExceptionHandler  {

    /**
     * 基础异常
     */
    @ExceptionHandler(Exception.class)
    public ResponseErrorResult baseException(Exception e) {
        log.warn(e.getMessage(), e);
        return ResponseUtil.error(500,e.getMessage());
    }

    /**
     * 业务异常
     */
//    @ExceptionHandler(BusinessException.class)
//    public R businessException(BusinessException e) {
//        log.warn(e.getMessage(), e);
//        if (StringUtils.isNull(e.getCode())) {
//            return R.error(e.getMessage());
//        }
//        return R.error(e.getCode(), e.getMessage());
//    }
}
