package com.wrk.pcap.shared.core.exception;


import lombok.Getter;
import org.springframework.http.ProblemDetail;

import java.time.OffsetDateTime;
import java.util.Map;

@Getter
public final class ApiError {
    private final ErrorCode errorCode;
    private final String message;
    private final OffsetDateTime timestamp;
    private final Map<String, Object> extra;

    public ApiError(ErrorCode errorCode, String message, Map<String, Object> extra) {
        this.errorCode = errorCode;
        this.message = message;
        this.timestamp = OffsetDateTime.now();
        this.extra = extra;
    }

    public ProblemDetail toProblemDetail() {
        ProblemDetail pd = ProblemDetail.forStatusAndDetail(errorCode.getStatus(), message);
        pd.setDetail(errorCode.getDescription());
        pd.setProperty("code", errorCode.getCode());
        pd.setProperty("message", errorCode.getMessage());
        pd.setProperty("timestamp", timestamp);
        if(extra != null) extra.forEach(pd::setProperty);
        return pd;
    }
}
