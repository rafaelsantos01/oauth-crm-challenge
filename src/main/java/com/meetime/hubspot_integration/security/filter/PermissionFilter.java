package com.meetime.hubspot_integration.security.filter;

import com.meetime.hubspot_integration.token.TokenProvider;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class PermissionFilter {


    private final TokenProvider tokenProvider;

    public boolean hasToken() {
        return tokenProvider.getToken().isPresent();
    }
}
