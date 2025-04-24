package com.meetime.hubspot_integration.controller;

import com.meetime.hubspot_integration.dto.ContactResponseDTO;
import com.meetime.hubspot_integration.dto.CreateContactRequestDTO;
import com.meetime.hubspot_integration.service.IHubSpotClientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/contacts")
@RequiredArgsConstructor
public class ContactsController {

    private final IHubSpotClientService hubspotClientService;

    @GetMapping
    @ResponseBody
    @PreAuthorize("@permissionFilter.hasToken()")
    public ResponseEntity<List<ContactResponseDTO>> listAllContacts() {
        List<ContactResponseDTO> contacts = hubspotClientService.listAllContacts();
        return new ResponseEntity<>(contacts, HttpStatus.OK);
    }

    @PostMapping
    @ResponseBody
    @PreAuthorize("@permissionFilter.hasToken()")
    public ResponseEntity<ContactResponseDTO> newContact(@RequestBody @Valid CreateContactRequestDTO dto) {
        ContactResponseDTO contact = hubspotClientService.newContact(dto);

        return new ResponseEntity<>(contact, HttpStatus.CREATED);
    }

    @DeleteMapping("/{contactId}")
    @ResponseBody
    @PreAuthorize("@permissionFilter.hasToken()")
    public ResponseEntity<Void> deleteContact(@PathVariable String contactId) {
        hubspotClientService.deleteContact(contactId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
