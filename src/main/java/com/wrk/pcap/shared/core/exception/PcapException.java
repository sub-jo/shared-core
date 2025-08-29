package com.wrk.pcap.shared.core.exception;

import lombok.Getter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

public abstract class PcapException extends Exception {
    @Getter
    private final HttpStatus status;
    @Getter
    private final Error error;
    public PcapException(HttpStatus status, Error error) {
        this.status = status;
        this.error = error;
    }
    public ResponseEntity<Error> getResponseEntity() {
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PROBLEM_JSON);
        return new ResponseEntity<>(error, headers, status);
    }
}
