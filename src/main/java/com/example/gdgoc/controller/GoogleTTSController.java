package com.example.gdgoc.controller;

import com.example.gdgoc.service.GoogleTTSService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@RestController
@RequestMapping("/api/tts")
public class GoogleTTSController {
    private static final String AUDIO_SAVE_DIR = "tts_audio_files";

    @Autowired
    private GoogleTTSService googleTTSService;

    @PostMapping(value = "/synthesize", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<ByteArrayResource> synthesizeSpeech(@RequestParam String text) throws IOException {
        ByteArrayResource audioResource = googleTTSService.synthesizeSpeech(text);

        // 음성 로컬 테스트시 주석 해제
        // String savedFilePath = saveAudioFile(audioResource.getByteArray(), text);
        // System.out.println("Audio file saved at: " + savedFilePath);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=tts_output.wav")
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .contentLength(audioResource.contentLength())
                .body(audioResource);
    }

    private String saveAudioFile(byte[] audioData, String text) throws IOException {
        File dir = new File(AUDIO_SAVE_DIR);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        String safeText = text.replaceAll("[^a-zA-Z0-9가-힣]", "_"); // 특수문자 제거
        String fileName = safeText.length() > 20 ? safeText.substring(0, 20) : safeText; // 파일명이 응답이 입력 텍스트라서 너무길면 자르기
        String filePath = AUDIO_SAVE_DIR + "/" + fileName + ".wav";

        try (FileOutputStream fos = new FileOutputStream(filePath)) {
            fos.write(audioData);
        }

        return filePath;
    }
}