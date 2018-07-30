package com.example.springboot.config.exception;

public class ValidationException extends Exception {

    private static final String MESSAGE = "参数验证失败，请检查.";

    public ValidationException() {
        super(MESSAGE);
    }

    public ValidationException(String message) {
        super(MESSAGE + System.getProperty("line.separator") + message);
    }
}
