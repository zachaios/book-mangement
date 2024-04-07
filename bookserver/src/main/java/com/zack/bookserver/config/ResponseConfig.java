package com.zack.bookserver.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@Slf4j
@ControllerAdvice
public class ResponseConfig implements ResponseBodyAdvice<Object> {


    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        if (returnType.hasMethodAnnotation(ResponseBody.class) || AnnotationUtils.findAnnotation(returnType.getContainingClass(), ResponseBody.class) != null) {
            return true;
        }
        return false;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        if(body instanceof ResponseResult){
            return body;
        }
//        if(body instanceof Exception){
//            return ResponseUtil.error(500,((Exception) body).getMessage());
//        }
        return ResponseUtil.success(body);
    }

    /**
     * 基础异常
     */
//    @ExceptionHandler(Exception.class)
//    public ResponseResult baseException(Exception e) {
//        log.warn(e.getMessage(), e);
//        return ResponseUtil.error(500,e.getMessage());
//    }
}
