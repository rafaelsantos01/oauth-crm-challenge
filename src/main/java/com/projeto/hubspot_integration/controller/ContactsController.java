package com.projeto.hubspot_integration.controller;

import com.projeto.hubspot_integration.dto.ContactResponseDTO;
import com.projeto.hubspot_integration.dto.CreateContactRequestDTO;
import com.projeto.hubspot_integration.service.IHubSpotClientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/contacts")
@RequiredArgsConstructor
public class ContactsController {

    private final IHubSpotClientService hubspotClientService;

    @GetMapping
    @ResponseBody
    public ResponseEntity<List<ContactResponseDTO>> listAllContacts() {
        List<ContactResponseDTO> contacts = hubspotClientService.listAllContacts();
        return new ResponseEntity<>(contacts, HttpStatus.OK);
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<ContactResponseDTO> newContact(@RequestBody @Valid CreateContactRequestDTO dto) {
        ContactResponseDTO contact = hubspotClientService.newContact(dto);

        return new ResponseEntity<>(contact, HttpStatus.CREATED);
    }


}
