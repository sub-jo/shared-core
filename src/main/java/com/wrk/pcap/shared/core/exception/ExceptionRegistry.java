package com.wrk.pcap.shared.core.exception;

import com.wrk.pcap.shared.core.exception.providers.ExceptionProvider;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ExceptionRegistry {
    private final Map<ErrorCode, ExceptionProvider> exceptionProviders;
    public ExceptionRegistry(List<ExceptionProvider> exceptionProviders) {
        this.exceptionProviders = exceptionProviders
                .stream().collect(Collectors.toUnmodifiableMap(ExceptionProvider::getErrorCode, p->p));
    }
    public PcapException create(ErrorCode errorCode,Object... args) {
        ExceptionProvider exceptionProvider = exceptionProviders.get(errorCode);
        if (exceptionProvider == null) {
            ApiError fallback = new ApiError(ErrorCode.INTERNAL_ERROR,"Unhandled error code: " + errorCode, null);
            return new PcapException(fallback);
        }
        return exceptionProvider.create(args);
    }
    public Optional<ExceptionProvider> providerFor(ErrorCode errorCode) {
        return Optional.ofNullable(exceptionProviders.get(errorCode));
    }
}
