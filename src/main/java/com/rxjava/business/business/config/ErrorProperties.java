package com.rxjava.business.business.config;

import com.rxjava.business.business.model.error.BusinessError;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

import java.util.HashMap;
import java.util.Map;

@Lazy
@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "application.error")
public class ErrorProperties {
    private Map<String, BusinessError> businessError;
}
