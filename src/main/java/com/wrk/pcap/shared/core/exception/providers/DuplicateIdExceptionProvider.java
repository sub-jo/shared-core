package com.wrk.pcap.shared.core.exception.providers;

import com.wrk.pcap.shared.core.exception.ApiError;
import com.wrk.pcap.shared.core.exception.ErrorCode;
import com.wrk.pcap.shared.core.exception.PcapException;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class DuplicateIdExceptionProvider implements ExceptionProvider {
    @Override
    public ErrorCode getErrorCode() {
        return ErrorCode.DUPLICATE_ID;
    }

    @Override
    public PcapException create(Object... args) {
        String entity = args.length > 0 ? args[0].toString() : "entity";
        String id = args.length > 1 ? args[1].toString() : "unknown";
        String detail = String.format("Entity '%s' with id '%s' already exists.", entity, id);
        ApiError apiError = new ApiError(getErrorCode(),detail, Map.of("entity",entity,"id",id));
        return new PcapException(apiError);
    }
}
