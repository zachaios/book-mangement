package com.zack.bookserver.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@Slf4j
@ControllerAdvice(basePackages = "com.zack.bookserver.controller")
public class ResponseConfig implements ResponseBodyAdvice<Object> {

//    private ObjectMapper objectMapper;
//
//    @PostConstruct
//    void init(){
//        objectMapper = new ObjectMapper();
////        objectMapper.configure(JsonGenerator.Feature.ESCAPE_NON_ASCII, true); // 对斜杠进行转义
//    }

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        if (
                !"error".equals(returnType.getMethod().getName())
        ) {
            return true;
        }
        return false;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
//        log.info("这次的body，",body);
//        log.info("这次的body空吗，",body==null);
//        log.info("这次的body空吗2，",ObjectUtils.isEmpty(body));
        try{
            if (body instanceof Page) {
                return body;
            } else if (body instanceof String || selectedConverterType == StringHttpMessageConverter.class) {
                /**
                 * 当Controller的方法返回值类型为String，HttpMessageConverter使用的是StringHttpMessageConverter，自然如果将响应体包装为Result后，会报java.lang.ClassCastException异常。
                 * 解决方案
                 * 我们可以将统一处理的响应体转换为Json字符串。
                 */
                return ResponseUtil.success(body);
            } else if (!(body instanceof ResponseResult)) {
                return ResponseUtil.success(body);
            } else if (ObjectUtils.isEmpty(body)) {
                return ResponseUtil.error(500,"空的");

            }
        }catch (Exception e){
            e.printStackTrace();
            log.warn("返回提转换报错，"+ e.getMessage());
        }
//        if(body instanceof Exception){
//            return ResponseUtil.error(500,((Exception) body).getMessage());
//        }
        return body;
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
