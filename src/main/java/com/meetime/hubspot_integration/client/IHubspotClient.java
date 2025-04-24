package com.meetime.hubspot_integration.client;

import com.meetime.hubspot_integration.client.config.HubspotClientConfiguration;
import com.meetime.hubspot_integration.client.dto.ContactResult;
import com.meetime.hubspot_integration.client.dto.ContactsResponse;
import com.meetime.hubspot_integration.client.dto.ResponseAccessToken;
import feign.FeignException;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
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

    @PostMapping("/crm/v3/objects/contacts")
    ContactResult createContact(@RequestBody Object request) throws FeignException;

    @GetMapping("/crm/v3/objects/contacts")
    ContactsResponse getAllContacts() throws FeignException;
}
