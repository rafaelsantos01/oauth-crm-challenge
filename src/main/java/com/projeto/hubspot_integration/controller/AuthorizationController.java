package com.projeto.hubspot_integration.controller;

import com.projeto.hubspot_integration.service.IAuthorizationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import java.net.MalformedURLException;

@RestController
@RequestMapping("/api/v1/oauth")
@AllArgsConstructor
public class AuthorizationController {

    private final IAuthorizationService authorizationService;

    @GetMapping("/authorization")
    public RedirectView handleAuthorizationUrl() throws MalformedURLException {

        return new RedirectView(authorizationService.getAuthorizationUrl());
    }

    @GetMapping("/callback")
    public void handleTokenAccess(@RequestParam String code){

        authorizationService.getAccessToken(code);
    }
}
