package com.example.gdgoc.service;

import lombok.extern.slf4j.Slf4j;
import net.nurigo.sdk.NurigoApp;
import net.nurigo.sdk.message.model.Message;
import net.nurigo.sdk.message.request.SingleMessageSendingRequest;
import net.nurigo.sdk.message.response.SingleMessageSentResponse;
import net.nurigo.sdk.message.service.DefaultMessageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SendMessageService {

    private final String fromPhoneNumber = "01094174818";
    private final String toPhoneNumber = "01030720135";

    private final DefaultMessageService defaultMessageService;

    public SendMessageService(
            @Value("${message.api-key}") String apiKey,
            @Value("${message.api-secret}") String apiSecret
    ) {
        this.defaultMessageService = NurigoApp.INSTANCE.initialize(apiKey, apiSecret, "https://api.coolsms.co.kr");
    }

    public void sendMessage(final String text) {
        Message message = new Message();
        message.setFrom(fromPhoneNumber);
        message.setTo(toPhoneNumber);
        message.setText(text);
        SingleMessageSentResponse response = this.defaultMessageService.sendOne(
                new SingleMessageSendingRequest(message));
        System.out.println(response);
    }
}
