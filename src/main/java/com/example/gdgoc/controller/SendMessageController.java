package com.example.gdgoc.controller;

import com.example.gdgoc.dto.MessageRequestDto;
import com.example.gdgoc.service.SendMessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class SendMessageController {

    private final SendMessageService sendMessageService;

    @PostMapping("/messages")
    public ResponseEntity<Void> sendMessage(@RequestBody final MessageRequestDto.MessageTextDto messageTextDto) {
        sendMessageService.sendMessage(messageTextDto.getText());
        return ResponseEntity.ok().build();
    }
}
