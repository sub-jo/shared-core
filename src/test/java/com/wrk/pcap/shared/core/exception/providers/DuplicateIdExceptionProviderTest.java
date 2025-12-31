package com.wrk.pcap.shared.core.exception.providers;

import com.wrk.pcap.shared.core.exception.ApiError;
import com.wrk.pcap.shared.core.exception.ErrorCode;
import com.wrk.pcap.shared.core.exception.PcapException;
import org.junit.jupiter.api.Test;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class DuplicateIdExceptionProviderTest {
    private final DuplicateIdExceptionProvider provider = new DuplicateIdExceptionProvider();

    @Test
    void create_shouldReturnPcapDuplicateIdExceptionProvider() {
        PcapException pcapException = provider.create("My","33");
        assertNotNull(pcapException);
        assertEquals(ErrorCode.DUPLICATE_ID, pcapException.getError().getErrorCode());
        assertTrue(pcapException.getMessage().contains("My"));
        var pd = pcapException.getError().toProblemDetail();
        assertNotNull(pd);
        assertNotNull(pd.getProperties());
        assertEquals(pd.getProperties().get("code"), ErrorCode.DUPLICATE_ID.getCode());
    }
}