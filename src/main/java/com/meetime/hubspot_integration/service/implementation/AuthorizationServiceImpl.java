package com.meetime.hubspot_integration.service.implementation;

import com.meetime.hubspot_integration.client.IHubspotClient;
import com.meetime.hubspot_integration.client.dto.ResponseAccessToken;
import com.meetime.hubspot_integration.dto.HubspotProperties;
import com.meetime.hubspot_integration.service.IAuthorizationService;
import com.meetime.hubspot_integration.token.TokenProvider;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.net.MalformedURLException;
import java.net.URL;

@Slf4j
@Service
@AllArgsConstructor
public class AuthorizationServiceImpl implements IAuthorizationService {

    private final HubspotProperties hubspotProperties;

    private final IHubspotClient hubspotClient;

    private final TokenProvider tokenProvider;

    @Override
    public String getAuthorizationUrl() throws MalformedURLException {
        String scope = hubspotProperties.getScopes().replace(",", "%20");
        String urlBase  = hubspotProperties.getBaseUrl() + hubspotProperties.getAuthorizeUrl();

        URL url = new URL(urlBase +
                "?client_id=" + hubspotProperties.getClientId() +
                "&scope=" + scope +
                "&redirect_uri=" + hubspotProperties.getRedirectUri());

        log.info("Authorization URL: {}", url);
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
        tokenProvider.setToken(authentication.getAccessToken());
        tokenProvider.setRefreshToken(authentication.getRefreshToken());
        tokenProvider.setExpires_in(authentication.getExpiresIn());

        log.info("Authentication successful: {}", authentication.getAccessToken());
    }
}
