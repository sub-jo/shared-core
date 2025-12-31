package com.wrk.pcap.shared.core.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(PcapException.class)
    public ResponseEntity<Object> handleException(PcapException ex, HttpServletRequest req) {
        ApiError apiError = ex.getError();
        var pd = apiError.toProblemDetail();
        String correlationId = MDC.get("correlationId");
        if (correlationId != null) pd.setProperty("correlationId", correlationId);
        log.warn("Handled PcapException code={} correlationId={} path={} detail={}",
                apiError.getErrorCode().getCode(), correlationId, req.getRequestURI(), apiError.getMessage());
        return ResponseEntity.status(apiError.getErrorCode().getStatus()).body(pd);
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleOtherException(Exception ex, HttpServletRequest req) {
        String correlationId = MDC.get("correlationId");
        log.warn("Handled Exception  correlationId={} path={}", correlationId, req.getRequestURI(), ex);
        ApiError apiError = new ApiError(ErrorCode.INTERNAL_ERROR, "An unexpected error occurred", null);
        var pd = apiError.toProblemDetail();
        if (correlationId != null) pd.setProperty("correlationId", correlationId);
        return ResponseEntity.status(apiError.getErrorCode().getStatus()).body(pd);
    }

}
