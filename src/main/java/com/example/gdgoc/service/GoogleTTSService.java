package com.example.gdgoc.service;

import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;

import java.util.HashMap;
import java.util.Map;
import java.util.Base64;

@Service
public class GoogleTTSService {
    private static final String GOOGLE_TTS_URL = "https://texttospeech.googleapis.com/v1/text:synthesize";
    @Autowired
    private GoogleApiKeyProvider apiKeyProvider;

    private final RestTemplate restTemplate = new RestTemplate();

    public ByteArrayResource synthesizeSpeech(String text) {
        Map<String, Object> requestBody = new HashMap<>();
        Map<String, Object> input = new HashMap<>();
        Map<String, Object> voice = new HashMap<>();
        Map<String, Object> audioConfig = new HashMap<>();

        input.put("text", text); // 여기가 텍스트 입력
        voice.put("languageCode", "ko-KR");
        voice.put("name", "ko-KR-Standard-B"); // 목소리 선택지 참고 : https://cloud.google.com/text-to-speech/docs/voices?hl=ko
        audioConfig.put("audioEncoding", "LINEAR16");

        requestBody.put("input", input);
        requestBody.put("voice", voice);
        requestBody.put("audioConfig", audioConfig);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        String url = GOOGLE_TTS_URL + "?key=" + apiKeyProvider.getApiKey();

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(requestBody, headers);
        ResponseEntity<Map> response = restTemplate.exchange(url, HttpMethod.POST, entity, Map.class);

        if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
            String base64Audio = (String) response.getBody().get("audioContent");
            byte[] audioBytes = Base64.getDecoder().decode(base64Audio);
            return new ByteArrayResource(audioBytes);
        } else {
            throw new RuntimeException("TTS API 요청 실패: " + response.getStatusCode());
        }
    }
}
