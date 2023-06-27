package com.company.chatterbook.controller;

public class ControllerExceptionHandler extends RuntimeException {
    public ControllerExceptionHandler(String message) {
        super(message);
    }
}
