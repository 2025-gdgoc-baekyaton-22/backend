package com.example.gdgoc.service;

import com.example.gdgoc.dto.GeminiRequestDto;
import com.example.gdgoc.dto.GeminiResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.List;
import java.util.Map;

@Service
public class GeminiApiService {

    @Autowired
    private GoogleApiKeyProvider apiKeyProvider;
    private static final String API_URL = "https://generativelanguage.googleapis.com/v1/models/gemini-pro:generateContent?key=";
    private final RestTemplate restTemplate;
    private final HealthPromptService healthPromptService;

    public GeminiApiService(RestTemplate restTemplate, HealthPromptService healthPromptService) {
        this.restTemplate = restTemplate;
        this.healthPromptService = healthPromptService;
    }

    public Map<String, String> processSurvey(Map<String, String> responses) {
        String patientPrompt = healthPromptService.createPatientMessagePrompt(responses);
        String patientMessage = getGeminiResponse(patientPrompt);

        String protectorPrompt = healthPromptService.createProtectorReportPrompt(responses);
        String protectorReport = getGeminiResponse(protectorPrompt);
        
        return Map.of(
            "patientMessage", patientMessage,
            "protectorReport", protectorReport
        );
    }

    public String getGeminiResponse(String prompt) {
        RestTemplate restTemplate = new RestTemplate();
        GeminiRequestDto requestDto = new GeminiRequestDto(
            List.of(new GeminiRequestDto.Content(
                List.of(new GeminiRequestDto.Part(prompt))
            ))
        );

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<GeminiRequestDto> requestEntity = new HttpEntity<>(requestDto, headers);
        ResponseEntity<GeminiResponseDto> response = restTemplate.postForEntity(
            API_URL + apiKeyProvider.getGeminiApiKey(), requestEntity, GeminiResponseDto.class
        );

        if (response.getBody() != null && response.getBody().getCandidates() != null) {
            List<GeminiResponseDto.Candidate> candidates = response.getBody().getCandidates();
            if (!candidates.isEmpty() && candidates.get(0).getContent() != null) {
                List<GeminiResponseDto.Part> parts = candidates.get(0).getContent().getParts();
                if (!parts.isEmpty()) {
                    return parts.get(0).getText();
                }
            }
        }

        // 모니터링 앱으로 리포팅해야함
        return "오류 : 응답을 생성할 수 없음";
    }
}