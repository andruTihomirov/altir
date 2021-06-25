package org.altir.exception;

import java.text.MessageFormat;

public class OrderException extends RuntimeException {

    public OrderException(String s, Object...args) {
        super(MessageFormat.format(s, args));
    }
}
