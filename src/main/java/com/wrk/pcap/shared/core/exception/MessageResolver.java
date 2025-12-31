package com.wrk.pcap.shared.core.exception;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class MessageResolver {
    private final MessageSource messageSource;
    public MessageResolver(MessageSource messageSource) {
        this.messageSource = messageSource;
    }
    public String getMessage(ErrorCode code, Object[] args, Locale locale) {
        return messageSource.getMessage(code.getMessage(), args, code.getDescription(),locale);
    }
}
