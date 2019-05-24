package ru.bellintegrator.practice.response;

import io.swagger.annotations.ApiOperation;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@RestControllerAdvice
public class ResponseData implements ResponseBodyAdvice {

    @Override
    public boolean supports(MethodParameter methodParameter, Class aClass) {
        return methodParameter.getMethodAnnotation(ApiOperation.class) != null;
    }

    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        if (methodParameter.getParameterType() != void.class) {
            DataView dataView = new DataView();
            dataView.data = o;
            return dataView;
        }
        SuccessView successView = new SuccessView();
        successView.result = "success";
        return successView;
    }
}
