package com.chisom.notificationservice.utils;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "app.auth-service")
public class ConfigUtils {
    private String validateUrl;
    private String basicAuth;
}
