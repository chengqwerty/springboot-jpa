package com.example.springboot.config.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@ControllerAdvice(basePackages = "com.example.springboot.study")
public class AcmeControllerAdvice extends ResponseEntityExceptionHandler {

    /**
     * 查询失败异常
     * @param httpServletRequest
     * @param httpServletResponse
     * @param throwable
     * @return
     */
    @ExceptionHandler(QueryException.class)
    @ResponseBody
    ResponseEntity<?> handleControllerQueryException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Throwable throwable) {
        HttpStatus httpStatus = getStatus(httpServletRequest);
        StatusBean statusBean = new StatusBean(400, throwable.getMessage());
        return new ResponseEntity<>(statusBean, httpStatus);
    }

    /**
     * 保存失败异常
     * @param httpServletRequest
     * @param httpServletResponse
     * @param throwable
     * @return
     */
    @ExceptionHandler(SaveException.class)
    @ResponseBody
    ResponseEntity<?> handleControllerSaveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Throwable throwable) {
        HttpStatus httpStatus = getStatus(httpServletRequest);
        StatusBean statusBean = new StatusBean(100, throwable.getMessage());
        return new ResponseEntity<>(statusBean, httpStatus);
    }

    private HttpStatus getStatus(HttpServletRequest httpServletRequest) {
        Integer statusCode = (Integer)httpServletRequest.getAttribute("javax.servlet.error.status_code");
        if (statusCode == null) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return HttpStatus.valueOf(statusCode);
    }
}
