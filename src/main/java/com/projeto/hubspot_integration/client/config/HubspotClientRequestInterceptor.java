package com.projeto.hubspot_integration.client.config;
import com.projeto.hubspot_integration.token.HubspotTokenProvider;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class HubspotClientRequestInterceptor implements RequestInterceptor {

    private final HubspotTokenProvider tokenProvider;

    @Override
    public void apply(RequestTemplate template) {
        template.header("Accept", "application/json");

        if (!template.url().contains("/oauth/v1/token")) {
            tokenProvider.getToken().ifPresent(token ->
                    template.header("Authorization", "Bearer " + token)
            );
        }
    }
}
