package com.meetime.hubspot_integration.webhook;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@Slf4j
public class WebHookService {

    public void processWebhook(Map<String, Object> payload) {
        log.info("Recebido Webhook de criação de contato: {}", payload);
    }
}
