package com.wrk.pcap.shared.core.exception.exceptionBuilder;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import com.wrk.pcap.shared.core.exception.Error;

import java.util.Locale;

public interface ExceptionBuilder {
    MediaType exceptionContentType = MediaType.APPLICATION_PROBLEM_JSON;

    static ResponseEntity<Error> generateResponseEntity(Error e, HttpStatus status) {
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(exceptionContentType);
        return new ResponseEntity<>(e, headers, status);
    }
    static ResponseEntity<Error> generateResponseEntity(Exception e, HttpStatus status, HttpServletRequest request, MessageSource messageSource) {
        HttpStatus httpStatus = status == null ? HttpStatus.INTERNAL_SERVER_ERROR : status;
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(exceptionContentType);
        Error error = Error.builder()
                .message(e.getMessage())
                .code(status != null? status.getReasonPhrase() : null)
                .stackTrace(e.toString())
                .referenceError(request.getRequestURI())
                .type(e.getClass().getSimpleName())
                .build();
        return new ResponseEntity<>(error, headers, httpStatus);
    }

}
