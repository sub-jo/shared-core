package com.wrk.pcap.shared.core.config;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ApplicationPropertiesTest {
    @Test
    void givenNewApplicationProperties_whenLoad_thenReturnCorrectProperties() {
        ApplicationProperties applicationProperties = new ApplicationProperties();

        Assertions.assertNotNull(applicationProperties);
        Assertions.assertEquals(100,applicationProperties.getMaxRecordLimit());
    }

}