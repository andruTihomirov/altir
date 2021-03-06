package org.altir.exception;

import java.text.MessageFormat;

public class NotFoundException extends RuntimeException {

    public NotFoundException(String s, Object...args) {
        super(MessageFormat.format(s, args));
    }

}
