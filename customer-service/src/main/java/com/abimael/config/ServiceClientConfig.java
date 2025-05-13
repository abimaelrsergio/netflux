package com.abimael.config;

import org.slf4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.context.annotation.*;
import org.springframework.web.client.*;

@Configuration
public class ServiceClientConfig {

    private static final Logger log = LoggerFactory.getLogger(ServiceClientConfig.class);

    @Bean
    public RestClient restClient(@Value("${movie.service.url}") String baseUrl) {
        log.info("movie service url: {}", baseUrl);
        return RestClient.builder()
                .baseUrl(baseUrl)
                .build();
    }
}
