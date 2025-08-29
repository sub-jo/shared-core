package com.wrk.pcap.shared.core.component;


import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public abstract class TestBase {
    @BeforeAll
    public abstract void setup();
    @AfterAll
    public void tearDown() {
        Mockito.clearAllCaches();
    }
}
