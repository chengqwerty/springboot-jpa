package com.example.springboot.config.exception;

public class QueryException extends Exception {

    private static final String MESSAGE = "内部错误,查询失败";

    public QueryException() {
        super(MESSAGE);
    }

    public QueryException(String message) {
        super(message);
    }
}
