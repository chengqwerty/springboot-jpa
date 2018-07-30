package com.example.springboot.config.exception;

public class ParameterValidationException extends Exception {

    private static final String MESSAGE = "参数验证失败，请检查.";

    public ParameterValidationException() {
        super(MESSAGE);
    }

    public ParameterValidationException(String message) {
        super(MESSAGE + System.getProperty("line.separator") + message);
    }
}
