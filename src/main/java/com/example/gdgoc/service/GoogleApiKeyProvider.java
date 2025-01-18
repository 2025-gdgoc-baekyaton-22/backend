package com.example.gdgoc.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class GoogleApiKeyProvider {
    @Value("${google.api.key}")
    private String apiKey;

    public String getApiKey() {
        return apiKey;
    }
}
