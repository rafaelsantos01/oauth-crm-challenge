package com.projeto.hubspot_integration.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("hubspot")

@Getter
@Setter
public class HubspotProperties {

    private String clientId;
    private String clientSecret;
    private String scopes;
    private String redirectUri;
    private String authUrl;
    private String tokenUrl;
}
