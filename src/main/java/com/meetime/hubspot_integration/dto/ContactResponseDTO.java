package com.meetime.hubspot_integration.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ContactResponseDTO {

    private String id;

    private String email;

    private String firstName;

    private String lastName;

    private String createdAt;
}
