package com.wrk.pcap.shared.core.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {
    DUPLICATE_ID("400.422-1", HttpStatus.BAD_REQUEST, "error.duplicateId", "Duplicate resource"),
    INVALID_IF_MATCH("400.428-2", HttpStatus.PRECONDITION_FAILED, "error.invalidIfMatch", "Invalid If-Match header"),
    VALIDATION_FAILED("400.422-3", HttpStatus.BAD_REQUEST, "error.validationFailed", "Validation failed"),
    NOT_FOUND("400.404", HttpStatus.NOT_FOUND, "error.notFound", "Resource not found"),
    INTERNAL_ERROR("500.0", HttpStatus.INTERNAL_SERVER_ERROR, "error.internal", "Internal server error");
    private final String code;
    private final HttpStatus status;
    private final String message;
    private final String description;

    ErrorCode(String code, HttpStatus status, String message, String description) {
        this.code = code;
        this.status = status;
        this.message = message;
        this.description = description;
    }
}
