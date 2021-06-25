package org.altir.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@ControllerAdvice
@Slf4j
public class ControllerExceptionHandler {

    @ExceptionHandler(value = {NotFoundException.class})
    public ResponseEntity<ExceptionInfo> handleNotFoundException(NotFoundException e, WebRequest request) {
        ExceptionInfo exceptionInfo = ExceptionInfo.builder()
                .code(NOT_FOUND.value())
                .message(e.getMessage())
                .className(e.getClass().getName())
                .url(request.getDescription(false))
                .build();
        return new ResponseEntity<>(exceptionInfo, NOT_FOUND);
    }

    @ExceptionHandler(value = {OrderException.class})
    public ResponseEntity<ExceptionInfo> handleOrderException(OrderException e, WebRequest request) {
        ExceptionInfo exceptionInfo = ExceptionInfo.builder()
                .code(BAD_REQUEST.value())
                .message(e.getMessage())
                .className(e.getClass().getName())
                .url(request.getDescription(false))
                .build();
        return new ResponseEntity<>(exceptionInfo, BAD_REQUEST);
    }

}
