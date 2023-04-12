package com.example.cqrs.Rest;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Serializable>> handleMethodArgumentNotValidException (HttpServletRequest httpServletRequest,
                                                                                            MethodArgumentNotValidException exception){
        Map<String, Serializable> map = new LinkedHashMap<>();
        map.put("timestamp", ZonedDateTime.now());
        map.put("status", exception.getStatusCode());
        map.put("error", exception.getDetailMessageCode());
        map.put("path", httpServletRequest.getRequestURL());

        return ResponseEntity.badRequest().body(map);

    }


}
