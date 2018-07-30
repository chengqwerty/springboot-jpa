package com.example.springboot.config.exception;

public class SaveException extends Exception{

    private static final String MESSAGE = "内部错误,保存失败";

    public SaveException() {
        super(MESSAGE);
    }

    public SaveException(String message) {
        super(message);
    }
}
