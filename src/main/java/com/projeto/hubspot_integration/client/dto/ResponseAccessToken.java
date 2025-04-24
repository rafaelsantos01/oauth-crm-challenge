package com.projeto.hubspot_integration.client.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
public class ResponseAccessToken {

    private String access_token;

    private String refresh_token;

    private Integer expires_in;

    private String token_type;

}
