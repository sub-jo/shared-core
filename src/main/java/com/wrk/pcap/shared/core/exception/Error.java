package com.wrk.pcap.shared.core.exception;


import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Error {
    private String message;
    private String code;
    private String referenceError;
    private String stackTrace;
    private String reason;
    private String type;
}
