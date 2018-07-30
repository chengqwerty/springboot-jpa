package com.example.springboot.config.exception;

public class DeleteException extends Exception {

    private static final String MESSAGE = "内部错误删除失败";

    public DeleteException() {
        super(MESSAGE);
    }

    public DeleteException(String message) {
        super(message);
    }
}
