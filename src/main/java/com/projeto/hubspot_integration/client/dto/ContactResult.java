package com.projeto.hubspot_integration.client.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
public class ContactResult {
    private String id;
    private ContactProperties properties;
}
