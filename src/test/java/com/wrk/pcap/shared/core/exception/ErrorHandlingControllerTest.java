package com.wrk.pcap.shared.core.exception;

import com.wrk.pcap.shared.core.component.TestBase;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;

import java.util.Optional;


class ErrorHandlingControllerTest extends TestBase {
    ErrorHandlingController errorHandlingController;
    HttpServletRequest request= Mockito.mock(HttpServletRequest.class);
    HttpServletResponse response= Mockito.mock(HttpServletResponse.class);
    MessageSource messageSource= Mockito.mock(MessageSource.class);

    private static final String LOGGER_NAME = "com.wrk.pcap.shared.core.exception.ErrorHandlingController";

    @Override
    @BeforeAll
    public void setup() {
        errorHandlingController = new ErrorHandlingController(messageSource);
        ResponseEntity<Error> errorResponseEntity = generateErrorResponse();
    }
    @BeforeEach
    public void setupTest() {
        Logger logger =  LoggerFactory.getLogger(LOGGER_NAME);
    }

    private ResponseEntity<Error> generateErrorResponse() {
        Error error = Error.builder().code("Bad Request").reason("reason").message("message")
                .referenceError("referenceError").stackTrace("stack").type("type")
                .build();
        return ResponseEntity.of(Optional.of(error));
    }
    private IllegalArgumentException generateIllegalArgumentException() {
        return new IllegalArgumentException("message");
    }

    @Override
    @AfterAll
    public void tearDown() {
        Logger logger =  LoggerFactory.getLogger(LOGGER_NAME);
        super.tearDown();
    }
    @Test
    public void handleGeneralRuntimeException() {
        HttpRequestMethodNotSupportedException exception = Mockito.mock(HttpRequestMethodNotSupportedException.class);
        Mockito.when(request.getMethod()).thenReturn("GET");
        Mockito.when(request.getRequestURI()).thenReturn("/uri");
        ResponseEntity<Error> errorResponseEntity = errorHandlingController.handleIllegalArgumentException(
                generateIllegalArgumentException(),request
        );
        ResponseEntity<Error> pcapException = generateErrorResponse();
        Assertions.assertNotNull(errorResponseEntity);
        Assertions.assertNotNull(errorResponseEntity.getBody());
        Assertions.assertEquals(HttpStatus.BAD_REQUEST.value(), errorResponseEntity.getStatusCode().value());
        Assertions.assertEquals(pcapException.getBody().getCode(), errorResponseEntity.getBody().getCode());
        Assertions.assertEquals(pcapException.getBody().getMessage(), errorResponseEntity.getBody().getMessage());
    }
}