package com.example.gdgoc.utils;

import com.example.gdgoc.user.dto.StatusRequestDTO;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class SurveyDataConverter {
    private static final Map<Integer, String> QUESTION_MAP = Map.of(
            1, "현재 몸 상태가 어떠신가요?",
            2, "오늘 기분이 어떠세요? 우울하거나 불안하진 않으신가요?",
            3, "요즘 친구나 가족과의 만남이나 연락은 어떻게 느끼세요?",
            4, "수면 중에 자주 깨시나요?",
            5, "식사는 잘 하셨나요?",
            6, "복용 중인 약이 있다면 빠뜨리지 않고 잘 드시고 계세요?",
            7, "오늘 병원에 다녀오셨나요?",
            8, "오늘 깜빡깜빡하거나, 길을 잃거나, 날짜를 헷갈리신 적이 있나요?"
    );

    private static final Map<Integer, Function<StatusRequestDTO, Integer>> FIELD_MAPPINGS = Map.of(
            1, StatusRequestDTO::getCurrentBodyStatus,
            2, StatusRequestDTO::getFeeling,
            3, StatusRequestDTO::getCommunicate,
            4, StatusRequestDTO::getSleep,
            5, StatusRequestDTO::getMeal,
            6, StatusRequestDTO::getMedication,
            7, StatusRequestDTO::getHospital,
            8, StatusRequestDTO::getMemory
    );

    private static final Map<Integer, SurveyResponseType> RESPONSE_TYPES = Map.of(
            1, SurveyResponseType.BODY_STATUS,
            2, SurveyResponseType.BODY_STATUS,
            3, SurveyResponseType.BODY_STATUS,
            4, SurveyResponseType.YES_NO,
            5, SurveyResponseType.YES_NO,
            6, SurveyResponseType.BODY_STATUS,
            7, SurveyResponseType.YES_NO,
            8, SurveyResponseType.BODY_STATUS
    );

    public static Map<String, String> convertSurveyData(StatusRequestDTO dto) {
        return FIELD_MAPPINGS.entrySet().stream()
                .collect(Collectors.toMap(
                        entry -> QUESTION_MAP.get(entry.getKey()), // 질문 문자열
                        entry -> RESPONSE_TYPES.get(entry.getKey()).getResponse(entry.getValue().apply(dto))
                ));
    }
}