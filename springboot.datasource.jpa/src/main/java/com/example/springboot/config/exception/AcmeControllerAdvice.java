package com.example.springboot.config.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@ControllerAdvice
public class AcmeControllerAdvice {

    /**
     *
     * @param httpServletRequest
     * @param httpServletResponse
     * @param throwable
     * @return
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseBody
    ResponseEntity<?> handleControllerMethodArgumentNotValidException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Throwable throwable) {
        HttpStatus httpStatus = getStatus(httpServletRequest);
        return new ResponseEntity(httpStatus);
    }

    private HttpStatus getStatus(HttpServletRequest httpServletRequest) {
        Integer statusCode = (Integer)httpServletRequest.getAttribute("javax.servlet.error.status_code");
        if (statusCode == null) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return HttpStatus.valueOf(statusCode);
    }
}
