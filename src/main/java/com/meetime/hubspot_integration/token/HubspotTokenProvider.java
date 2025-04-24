package com.meetime.hubspot_integration.token;

import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Setter
public class HubspotTokenProvider {

    private String token;
    private String refreshToken;
    private int expires_in;


    public Optional<String> getToken() {
        return Optional.ofNullable(token);
    }

    public void clearToken() {
        token = null;
        refreshToken = null;
        expires_in = 0;
    }

}
