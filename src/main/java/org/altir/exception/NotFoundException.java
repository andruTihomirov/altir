package org.altir.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.text.MessageFormat;

public class NotFoundException extends RuntimeException {

    public NotFoundException(String s) {
        super(s);
    }

    public NotFoundException(String s, Object...args) {
        super(MessageFormat.format(s, args));
    }

}
