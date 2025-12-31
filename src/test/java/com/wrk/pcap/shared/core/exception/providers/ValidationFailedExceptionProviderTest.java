package com.wrk.pcap.shared.core.exception.providers;

import com.wrk.pcap.shared.core.exception.ApiError;
import com.wrk.pcap.shared.core.exception.ErrorCode;
import com.wrk.pcap.shared.core.exception.PcapException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import java.util.*;

class ValidationFailedExceptionProviderTest {
    private final ValidationFailedExceptionProvider provider = new ValidationFailedExceptionProvider();
    @Test
    void create_shouldReturnValidationExceptionProvider(){
        Map<String, Object> extras = Map.of("field", "name", "error", "must not be blank");
        PcapException pcapException = provider.create("Validation failed for request",extras);
        ApiError apiError = pcapException.getError();
        assertNotNull(apiError);
        assertEquals(ErrorCode.VALIDATION_FAILED,apiError.getErrorCode());
        assertEquals("Validation failed for request", apiError.getMessage());
        assertEquals(extras, apiError.getExtra());
        var pd = apiError.toProblemDetail();
        assertNotNull(pd);
        assertEquals(pd.getProperties().get("code").equals(ErrorCode.VALIDATION_FAILED),pd.getProperties().get("code").equals(ErrorCode.VALIDATION_FAILED));
    }

}