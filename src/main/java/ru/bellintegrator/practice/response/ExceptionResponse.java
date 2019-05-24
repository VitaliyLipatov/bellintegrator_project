package ru.bellintegrator.practice.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.bellintegrator.practice.exception.RecordNotFoundException;
import ru.bellintegrator.practice.exception.WrongRequestException;

@RestControllerAdvice
public class ExceptionResponse {

    @ExceptionHandler({WrongRequestException.class, RecordNotFoundException.class,
            HttpMessageNotReadableException.class, HttpRequestMethodNotSupportedException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorView exceptionHandler(RuntimeException e) {
        ErrorView errorView = new ErrorView();
        errorView.error = e.getMessage();
        return errorView;
    }

    @ExceptionHandler({Exception.class})
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorView unexpectedExceptionHandler(Exception e) {
        ErrorView errorView = new ErrorView();
        errorView.error = "Unexpected error. ";
        return errorView;
    }
}
