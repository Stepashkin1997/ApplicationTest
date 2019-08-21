package ru.eltex.app.exceptions;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.UnsatisfiedServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.io.IOException;

@ControllerAdvice
public class MyExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NullPointerException.class)
    protected String handleNullExceptions(NullPointerException ex) {
        return "error";
    }

    @ExceptionHandler(UnsatisfiedServletRequestParameterException.class)
    private String wrongWay() {
        return "error";
    }

    @ExceptionHandler(IOException.class)
    private String corruptedFile() {
        return "error";
    }
}
