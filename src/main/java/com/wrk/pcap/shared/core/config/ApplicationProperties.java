package com.wrk.pcap.shared.core.config;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "application")
public class ApplicationProperties {

    @NotNull(message = "maxRecordLimit value cannot be null!")
    private Integer maxRecordLimit = 100;
}
