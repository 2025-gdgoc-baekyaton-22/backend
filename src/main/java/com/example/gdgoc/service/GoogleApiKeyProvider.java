package com.example.gdgoc.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class GoogleApiKeyProvider {
    @Value("${google.api.key}")
    private String apiKey;

    @Value("${gemini.api.key}")
    private String GeminiApiKey;

    public String getApiKey() {
        return apiKey;
    }

    public String getGeminiApiKey() {
        return GeminiApiKey;
    }
}
