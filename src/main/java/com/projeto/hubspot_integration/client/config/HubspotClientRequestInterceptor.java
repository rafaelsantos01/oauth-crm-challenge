package com.projeto.hubspot_integration.client.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;

public class HubspotClientRequestInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate template) {
        template.header("Accept", "application/json");
    }
}
