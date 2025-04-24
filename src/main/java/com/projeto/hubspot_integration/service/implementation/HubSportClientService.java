package com.projeto.hubspot_integration.service.implementation;

import com.projeto.hubspot_integration.client.IHubspotClient;
import com.projeto.hubspot_integration.client.dto.ContactResult;
import com.projeto.hubspot_integration.client.dto.ContactsResponse;
import com.projeto.hubspot_integration.dto.ContactResponseDTO;
import com.projeto.hubspot_integration.dto.CreateContactRequestDTO;
import com.projeto.hubspot_integration.service.IHubSpotClientService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class HubSportClientService implements IHubSpotClientService {

    private final IHubspotClient hubspotClient;

    @Override
    public List<ContactResponseDTO> listAllContacts() {
        List<ContactResponseDTO> contactResponseDTOList = new ArrayList<>();
        ContactsResponse contacts = hubspotClient.getAllContacts();

        for (ContactResult contact : contacts.getResults()) {

            ContactResponseDTO contactResponseDTO =ContactResponseDTO
                    .builder()
                    .id(contact.getId())
                    .firstName(contact.getProperties().getFirstName())
                    .lastName(contact.getProperties().getLastName())
                    .email(contact.getProperties().getEmail())
                    .createdAt(contact.getProperties().getCreatedAt())
                    .build();

            contactResponseDTOList.add(contactResponseDTO);
        }

        return contactResponseDTOList;
    }

    @Override
    public ContactResponseDTO newContact(CreateContactRequestDTO dto) {


        return null;
    }


}
