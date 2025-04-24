package com.meetime.hubspot_integration.client.config;

import com.meetime.hubspot_integration.token.TokenProvider;
import org.springframework.context.annotation.Bean;

public class HubspotClientConfiguration {

    @Bean
    public  HubspotClientRequestInterceptor  getAuthentication(TokenProvider tokenProvider) {
        return new HubspotClientRequestInterceptor(tokenProvider);
    }
}
