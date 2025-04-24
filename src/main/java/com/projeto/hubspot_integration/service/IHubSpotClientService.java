package com.projeto.hubspot_integration.service;

import com.projeto.hubspot_integration.dto.ContactResponseDTO;
import com.projeto.hubspot_integration.dto.CreateContactRequestDTO;

import java.util.List;

public interface IHubSpotClientService {

    List<ContactResponseDTO> listAllContacts();

    ContactResponseDTO newContact(CreateContactRequestDTO dto);
}
