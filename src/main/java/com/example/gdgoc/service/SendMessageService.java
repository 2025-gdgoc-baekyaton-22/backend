package com.example.gdgoc.service;

import com.example.gdgoc.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import net.nurigo.sdk.NurigoApp;
import net.nurigo.sdk.message.model.Message;
import net.nurigo.sdk.message.request.SingleMessageSendingRequest;
import net.nurigo.sdk.message.response.SingleMessageSentResponse;
import net.nurigo.sdk.message.service.DefaultMessageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SendMessageService {

    private final String fromPhoneNumber = "01094174818";
    private final String toPhoneNumber = "01030720135";

    private final DefaultMessageService defaultMessageService;
    private final UserService userService;

    public SendMessageService(
            @Value("${message.api-key}") String apiKey,
            @Value("${message.api-secret}") String apiSecret,
            UserService userService
    ) {
        this.defaultMessageService = NurigoApp.INSTANCE.initialize(apiKey, apiSecret, "https://api.coolsms.co.kr");
        this.userService = userService;
    }

    // 2번 보내야 되는 로직
    @Scheduled(cron = "0 13 5 * * *", zone = "Asia/Seoul")
//    @Scheduled(cron = "0 0 8,20 * * *", zone = "Asia/Seoul") // 매일 08:00, 20:00에 실행
    public void send2Message() {
        System.out.println("하하");
        userService.findAllUserData().stream()
                .filter(data -> !data.isThreeOrTwo())
                .forEach(data -> {
                    String text = String.format("http://dollbomzigi.store/%d 로 접속하세요!", data.getId());
                    sendMessage(data.getCareGiverPhone(), data.getCareTakerPhone(), text);
                });
    }

    // 3번 보내야 되는 로직
    @Scheduled(cron = "0 0 8,14,20 * * *", zone = "Asia/Seoul") // 매일 08:00, 14:00, 20:00에 실행
    public void send3Message() {
        userService.findAllUserData().stream()
                .filter(data -> !data.isThreeOrTwo())
                .forEach(data -> {
                    String text = String.format("http://dollbomzigi.store/%d 로 접속하세요!", data.getId());
                    sendMessage(data.getCareGiverPhone(), data.getCareTakerPhone(), text);
                });
    }

    public void sendMessage(String fromPhoneNumber, String toPhoneNumber, String text) {
        Message message = new Message();
        message.setFrom(fromPhoneNumber);
        message.setTo(toPhoneNumber);
        message.setText(text);
        SingleMessageSentResponse response = this.defaultMessageService.sendOne(
                new SingleMessageSendingRequest(message));
    }
}
