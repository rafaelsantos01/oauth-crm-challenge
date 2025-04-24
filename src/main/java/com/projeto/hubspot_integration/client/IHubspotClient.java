package com.projeto.hubspot_integration.client;

import com.projeto.hubspot_integration.client.config.HubspotClientConfiguration;
import com.projeto.hubspot_integration.client.dto.ResponseAccessToken;
import feign.FeignException;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
        contextId = "hubspotContext",
        name = "hubspotClient",
        url = "${hubspot.base-url-api}",
        configuration = HubspotClientConfiguration.class)
public interface IHubspotClient {

    @PostMapping("/oauth/v1/token")
    ResponseAccessToken getAuthentication(@RequestBody MultiValueMap<String, String> request) throws FeignException;
}
