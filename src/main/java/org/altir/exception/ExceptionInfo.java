package org.altir.exception;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class ExceptionInfo {

    private final int code;

    private final String message;

    private final String className;

    private final String url;

}
