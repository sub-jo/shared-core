package com.wrk.pcap.shared.core.exception.exceptionBuilder;

import com.wrk.pcap.shared.core.exception.Error;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ExceptionBuilderTest {
    @Mock
    MessageSource messageSource;
    @Test
    void whenGenerateResponseEntityForRuntimeException_thenReturnResult(){
        String message = "message";
        String requestURI = "requestURI";
        String code = "Bad Request";
        Exception exception = Mockito.mock(Exception.class);
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpStatus httpStatus = Mockito.mock(HttpStatus.class);
        Mockito.when(exception.getMessage()).thenReturn(message);
        Mockito.when(request.getRequestURI()).thenReturn(requestURI);
        ResponseEntity<Error> res = ExceptionBuilder.generateResponseEntity(exception,HttpStatus.BAD_REQUEST,request,messageSource);
        Assertions.assertNotNull(res.getBody());
        Error error = res.getBody();
        assertEquals(message, error.getMessage());
        assertEquals(requestURI, error.getReferenceError());
        assertEquals(code, error.getCode());
    }
}