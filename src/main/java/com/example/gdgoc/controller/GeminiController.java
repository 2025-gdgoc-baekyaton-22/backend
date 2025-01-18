package com.example.gdgoc.controller;

import com.example.gdgoc.service.GeminiApiService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api/gemini")
public class GeminiController {
    private final GeminiApiService geminiApiService;

    public GeminiController(GeminiApiService geminiApiService) {
        this.geminiApiService = geminiApiService;
    }

    @PostMapping("/process-survey")
    public Map<String, String> processSurvey(@RequestBody Map<String, String> responses) {
        return geminiApiService.processSurvey(responses);
    }

    @PostMapping(value = "/generate", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> generateText(@RequestParam String prompt) {
        String geminiResponse = geminiApiService.getGeminiResponse(prompt);

        return ResponseEntity.ok()
            .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .body(geminiResponse);
    }
}