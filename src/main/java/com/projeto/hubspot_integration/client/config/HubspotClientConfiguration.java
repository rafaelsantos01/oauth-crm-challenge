package com.projeto.hubspot_integration.client.config;

import org.springframework.context.annotation.Bean;

public class HubspotClientConfiguration {

    @Bean
    public  HubspotClientRequestInterceptor  getAuthentication() {
        return new HubspotClientRequestInterceptor();
    }
}
