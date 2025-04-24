package com.meetime.hubspot_integration.client.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class ContactsResponse {
    private List<ContactResult> results;
}
