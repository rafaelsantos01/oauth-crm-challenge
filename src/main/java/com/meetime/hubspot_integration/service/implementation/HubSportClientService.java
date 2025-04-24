package com.meetime.hubspot_integration.service.implementation;

import com.meetime.hubspot_integration.client.IHubspotClient;
import com.meetime.hubspot_integration.client.dto.ContactResult;
import com.meetime.hubspot_integration.client.dto.ContactsResponse;
import com.meetime.hubspot_integration.dto.ContactResponseDTO;
import com.meetime.hubspot_integration.dto.CreateContactRequestDTO;
import com.meetime.hubspot_integration.service.IHubSpotClientService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        Map<String, Object> properties = new HashMap<>();
        properties.put("firstname", dto.getFirstName());
        properties.put("lastname", dto.getLastName());
        properties.put("email", dto.getEmail());
        properties.put("phone", dto.getPhone());


        Map<String, Object> payload = new HashMap<>();
        payload.put("properties", properties);

        ContactResult contact = hubspotClient.createContact(payload);

        return ContactResponseDTO
                .builder()
                .id(contact.getId())
                .firstName(contact.getProperties().getFirstName())
                .lastName(contact.getProperties().getLastName())
                .email(contact.getProperties().getEmail())
                .createdAt(contact.getProperties().getCreatedAt())
                .phone(contact.getProperties().getPhone())
                .build();
    }


}
