package com.projeto.hubspot_integration.client.config;

import com.projeto.hubspot_integration.token.HubspotTokenProvider;
import org.springframework.context.annotation.Bean;

public class HubspotClientConfiguration {

    @Bean
    public  HubspotClientRequestInterceptor  getAuthentication(HubspotTokenProvider tokenProvider) {
        return new HubspotClientRequestInterceptor(tokenProvider);
    }
}
