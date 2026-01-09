package com.wrk.pcap.shared.core.config.build;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

@Component
@Slf4j
public class GitPropertiesReader {
    public Properties read() {
        try {
            ClassPathResource cpr = new ClassPathResource("git.properties");
            if(!cpr.exists()) {
                cpr = new ClassPathResource("BOOT-INF/classes/git.properties");
                if (!cpr.exists()) return null;
            }
            try (InputStream in = cpr.getInputStream()){
                String content = new String(in.readAllBytes(), StandardCharsets.UTF_8);
                content = content.replaceAll("(?m)(git\\.build\\.user\\.email=[^\\n]+)git\\.build\\.user\\.name=", "$1\n git.build.user.name=");
                Properties p = new Properties();
                p.load(new StringReader(content));
                return p;
            }
        }
        catch (IOException e){
            log.warn("Can't read git.properties file",e);
            return null;
        }
    }
}
