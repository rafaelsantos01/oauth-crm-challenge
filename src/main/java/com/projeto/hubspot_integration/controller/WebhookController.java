package com.projeto.hubspot_integration.controller;

import com.projeto.hubspot_integration.webhook.WebHookService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/webhook")
@AllArgsConstructor
public class WebhookController {

    private final WebHookService webhookService;


     @PostMapping
     public ResponseEntity<Void> webhook(@RequestBody List<Map<String, Object>> payload) {
         payload.forEach(webhookService::processWebhook);

         return new ResponseEntity<>(HttpStatus.OK);
     }
}
