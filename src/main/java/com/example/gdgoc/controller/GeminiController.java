package com.example.gdgoc.controller;

import com.example.gdgoc.service.GeminiApiService;
import com.example.gdgoc.user.domain.Status;
import com.example.gdgoc.user.domain.SurveyStatus;
import com.example.gdgoc.user.dto.IsSuccessDTO;
import com.example.gdgoc.user.dto.StatusRequestDTO;
import com.example.gdgoc.user.service.UserService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api/gemini")
public class GeminiController {
    private final GeminiApiService geminiApiService;
    private final UserService userService;

    public GeminiController(GeminiApiService geminiApiService, UserService userService) {
        this.geminiApiService = geminiApiService;
        this.userService = userService;
    }


    @PostMapping("/process-survey")
    public Map<String, String> processSurvey(@RequestBody StatusRequestDTO dto) {
        try {
            Status status = Status.builder()
                    .dayTime(dto.getDayTime())
                    .meal(SurveyStatus.fromValue(dto.getMeal()))
                    .sleep(SurveyStatus.fromValue(dto.getSleep()))
                    .communicate(SurveyStatus.fromValue(dto.getCommunicate()))
                    .currentBodyStatus(SurveyStatus.fromValue(dto.getCurrentBodyStatus()))
                    .defecation(SurveyStatus.fromValue(dto.getCurrentBodyStatus()))
                    .feeling(SurveyStatus.fromValue(dto.getFeeling()))
                    .hospital(SurveyStatus.fromValue(dto.getHospital()))
                    .medication(SurveyStatus.fromValue(dto.getMedication()))
                    .memory(SurveyStatus.fromValue(dto.getMemory()))
                    .build();
            status.setUser(userService.getCurrentUser());
            userService.saveStatus(status);
        }
        catch(Exception e){
            throw e;
        }

        //return geminiApiService.processSurvey(responses);
        return null;
    }

    @PostMapping(value = "/generate", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> generateText(@RequestParam String prompt) {
        String geminiResponse = geminiApiService.getGeminiResponse(prompt);

        return ResponseEntity.ok()
            .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .body(geminiResponse);
    }
}