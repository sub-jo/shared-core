package com.wrk.pcap.shared.core.exception;

import com.wrk.pcap.shared.core.exception.providers.ExceptionProvider;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;



class ExceptionRegistryTest {

    @Test
    void create_shouldDelegateToProvider_whenProviderExists() {
        ExceptionProvider mockProvider = Mockito.mock(ExceptionProvider.class);
        Mockito.when(mockProvider.getErrorCode()).thenReturn(ErrorCode.INTERNAL_ERROR);
        Mockito.when(mockProvider.create("E","0")).thenReturn(
                new PcapException(new ApiError(ErrorCode.INTERNAL_ERROR,"internal",Map.of("id","0")))
        );
        ExceptionRegistry registry = new ExceptionRegistry(List.of(mockProvider));
        PcapException ex = registry.create(ErrorCode.INTERNAL_ERROR,"E","0");
        assertNotNull(ex);
        assertEquals(ErrorCode.INTERNAL_ERROR, ex.getError().getErrorCode());
        assertEquals(ex.getError().getExtra(), Map.of("id","0"));
    }
    @Test
    void create_shouldReturnFallback_whenProviderDoesNotExist() {
        ExceptionRegistry registry = new ExceptionRegistry(List.of());
        PcapException ex = registry.create(ErrorCode.DUPLICATE_ID,"E","0");
        assertNotNull(ex);
        assertEquals(ErrorCode.INTERNAL_ERROR, ex.getError().getErrorCode());
        assertTrue(ex.getError().getMessage().contains("Unhandled error code"));
    }
}