package com.wrk.pcap.shared.core.exception;


import com.wrk.pcap.shared.core.exception.exceptionBuilder.ExceptionBuilder;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class ErrorHandlingController {
    private static final String EXCEPTION_WHILE_PROCESSING = "Exception while processing request";
    private static final String EXCEPTION_WHILE_HANDLING = "Exception while handling request";
    private static final String DEFAULT_BLANK_MESSAGE = "Must not be blank";
    private static final String DEFAULT_NULLABLE_OR_NOT_BLANK_MESSAGE = "Must not be blank or null";
    private final MessageSource messageSource;

    public ErrorHandlingController(MessageSource messageSource) {
        this.messageSource = messageSource;
    }
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Error> handleIllegalArgumentException(IllegalArgumentException e, HttpServletRequest request) {
        log.error(EXCEPTION_WHILE_PROCESSING, e);
        return ExceptionBuilder.generateResponseEntity(e,HttpStatus.BAD_REQUEST,request,messageSource);
    }
}
