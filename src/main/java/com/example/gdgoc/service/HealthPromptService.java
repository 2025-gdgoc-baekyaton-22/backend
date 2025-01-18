package com.example.gdgoc.service;

import org.springframework.stereotype.Service;
import java.util.Map;

@Service
public class HealthPromptService {
    public String createPatientMessagePrompt(Map<String, String> responses) {
        return String.format(
                """
                사용자의 오늘 건강 상태를 분석하고 위로와 격려가 담긴 메시지를 작성해주세요.
                
                응답 데이터:
                - 신체 상태: %s
                - 배변/배뇨 상태: %s
                - 기분 상태: %s
                - 사회적 활동: %s
                - 약물 복용 상태: %s
                - 식사 상태: %s
                - 수면 상태: %s
                - 인지 상태: %s
                - 병원 방문 여부: %s

                이 데이터를 기반으로 사용자의 상태를 평가하고, 긍정적인 에너지를 줄 수 있는 메시지를 생성해주세요.
                """,
                responses.getOrDefault("현재 몸 상태가 어떠신가요?", "알 수 없음"),
                responses.getOrDefault("배변과 배뇨는 잘 하고 계세요?", "알 수 없음"),
                responses.getOrDefault("오늘 기분이 어떠세요? 우울하거나 불안하진 않으신가요?", "알 수 없음"),
                responses.getOrDefault("요즘 친구나 가족과의 만남이나 연락은 어떻게 느끼세요?", "알 수 없음"),
                responses.getOrDefault("복용 중인 약이 있다면 빠뜨리지 않고 잘 드시고 계세요?", "알 수 없음"),
                responses.getOrDefault("식사는 잘 하셨나요?", "알 수 없음"),
                responses.getOrDefault("수면 중에 자주 깨시나요?", "알 수 없음"),
                responses.getOrDefault("오늘 깜빡깜빡하거나, 길을 잃거나, 날짜를 헷갈리신 적이 있나요?", "알 수 없음"),
                responses.getOrDefault("오늘 병원에 다녀오셨나요?", "알 수 없음")
        );
    }

    public String createProtectorReportPrompt(Map<String, String> responses) {
        return String.format(
                """
                보호자를 위한 환자의 건강 상태 보고서를 작성해주세요.
                
                응답 데이터:
                - 신체 상태: %s
                - 배변/배뇨 상태: %s
                - 기분 상태: %s
                - 사회적 활동: %s
                - 약물 복용 상태: %s
                - 식사 상태: %s
                - 수면 상태: %s
                - 인지 상태: %s
                - 병원 방문 여부: %s

                보호자가 환자의 건강 상태를 쉽게 이해할 수 있도록 요약하고,
                보호자가 추가로 신경 써야 할 부분(예: 감정적 지원, 약물 복용 관리 등)에 대한 조언을 제공해주세요.
                """,
                responses.getOrDefault("현재 몸 상태가 어떠신가요?", "알 수 없음"),
                responses.getOrDefault("배변과 배뇨는 잘 하고 계세요?", "알 수 없음"),
                responses.getOrDefault("오늘 기분이 어떠세요? 우울하거나 불안하진 않으신가요?", "알 수 없음"),
                responses.getOrDefault("요즘 친구나 가족과의 만남이나 연락은 어떻게 느끼세요?", "알 수 없음"),
                responses.getOrDefault("복용 중인 약이 있다면 빠뜨리지 않고 잘 드시고 계세요?", "알 수 없음"),
                responses.getOrDefault("식사는 잘 하셨나요?", "알 수 없음"),
                responses.getOrDefault("수면 중에 자주 깨시나요?", "알 수 없음"),
                responses.getOrDefault("오늘 깜빡깜빡하거나, 길을 잃거나, 날짜를 헷갈리신 적이 있나요?", "알 수 없음"),
                responses.getOrDefault("오늘 병원에 다녀오셨나요?", "알 수 없음")
        );
    }
}