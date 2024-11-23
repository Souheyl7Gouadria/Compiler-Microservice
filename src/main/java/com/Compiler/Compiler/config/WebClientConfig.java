package com.Compiler.Compiler.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Value(Constants.BASE_URL)
    private String baseURL;

    @Bean
    public WebClient webClient(){

        return WebClient.builder()
                .baseUrl(baseURL)
                .defaultHeader("Content-Type", "application/json")
                .build();
    }
}
