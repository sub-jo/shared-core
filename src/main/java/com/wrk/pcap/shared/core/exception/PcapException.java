package com.wrk.pcap.shared.core.exception;


public class PcapException extends RuntimeException {
   private final ApiError apiError;

    public PcapException(ApiError apiError) {
        super(apiError != null ? apiError.getMessage() : null);
        this.apiError = apiError;
    }
    public ApiError getError() {
        return apiError;
    }
}
