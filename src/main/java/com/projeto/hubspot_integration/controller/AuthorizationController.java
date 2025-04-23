package com.projeto.hubspot_integration.controller;

import com.projeto.hubspot_integration.service.IAuthorizationService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping()
@AllArgsConstructor
public class AuthorizationController {

    private final IAuthorizationService authorizationService;


    @GetMapping("/authorization")
    public ResponseEntity<Object> handleAuthorizationUrl()  {
        authorizationService.getAuthorizationUrl();
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @GetMapping("/oauth-callback")
    public ResponseEntity<Object> handleTokenAccess() {
        authorizationService.getAccessToken();
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
