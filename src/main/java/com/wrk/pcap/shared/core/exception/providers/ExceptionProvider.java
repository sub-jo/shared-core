package com.wrk.pcap.shared.core.exception.providers;


import com.wrk.pcap.shared.core.exception.ErrorCode;
import com.wrk.pcap.shared.core.exception.PcapException;

import java.util.Objects;

public interface ExceptionProvider {
    ErrorCode getErrorCode();
    PcapException create(Object...args);
}
