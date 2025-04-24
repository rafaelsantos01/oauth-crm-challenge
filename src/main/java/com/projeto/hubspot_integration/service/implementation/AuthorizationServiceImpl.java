package com.projeto.hubspot_integration.service.implementation;

import com.projeto.hubspot_integration.client.IHubspotClient;
import com.projeto.hubspot_integration.client.dto.ResponseAccessToken;
import com.projeto.hubspot_integration.dto.HubspotProperties;
import com.projeto.hubspot_integration.service.IAuthorizationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.net.MalformedURLException;
import java.net.URL;

@Service
@AllArgsConstructor
public class AuthorizationServiceImpl implements IAuthorizationService {

    private final HubspotProperties hubspotProperties;

    private final IHubspotClient hubspotClient;

    @Override
    public String getAuthorizationUrl() throws MalformedURLException {
        String scope = hubspotProperties.getScopes().replace(",", "%20");
        String urlBase  = hubspotProperties.getBaseUrl() + hubspotProperties.getAuthorizeUrl();

        URL url = new URL(urlBase +
                "?client_id=" + hubspotProperties.getClientId() +
                "&scope=" + scope +
                "&redirect_uri=" + hubspotProperties.getRedirectUri());

        return url.toString();
    }

    @Override
    public void getAccessToken(String code) {
        MultiValueMap<String, String> request = new LinkedMultiValueMap<>();
        request.add("grant_type", "authorization_code");
        request.add("client_id", hubspotProperties.getClientId());
        request.add("client_secret", hubspotProperties.getClientSecret());
        request.add("redirect_uri", hubspotProperties.getRedirectUri());
        request.add("code", code);

        ResponseAccessToken authentication = hubspotClient.getAuthentication(request);

        System.out.println("Authentication: " + authentication.toString());
    }
}
