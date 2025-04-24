package com.meetime.hubspot_integration.service;

import com.meetime.hubspot_integration.dto.ContactResponseDTO;
import com.meetime.hubspot_integration.dto.CreateContactRequestDTO;

import java.util.List;

public interface IHubSpotClientService {

    List<ContactResponseDTO> listAllContacts();

    ContactResponseDTO newContact(CreateContactRequestDTO dto);

    void deleteContact(String contactId);
}
