package com.wrk.pcap.shared.core.exception.providers;

import com.wrk.pcap.shared.core.exception.ApiError;
import com.wrk.pcap.shared.core.exception.ErrorCode;
import com.wrk.pcap.shared.core.exception.PcapException;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class ValidationFailedExceptionProvider implements ExceptionProvider {

    @Override
    public ErrorCode getErrorCode() {
        return ErrorCode.VALIDATION_FAILED;
    }

    @Override
    @SuppressWarnings("unchecked")
    public PcapException create(Object... args) {
        String details = args.length > 0 ? args[0].toString() : "Validation failed";
        Map<String,Object> extra = args.length > 1 &&
                args[1] instanceof Map ? (Map<String, Object>) args[1]: Map.of();
        ApiError error = new ApiError(getErrorCode(),details,extra);
        return new PcapException(error);
    }
}
