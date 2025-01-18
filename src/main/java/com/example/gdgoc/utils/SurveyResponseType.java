package com.example.gdgoc.utils;

import lombok.Getter;
import java.util.Map;

@Getter
public enum SurveyResponseType {
    BODY_STATUS(Map.of(
            1, "VERY_GOOD",
            2, "NORMAL",
            3, "BAD",
            4, "EXCELLENT",
            5, "MID",
            6, "VERY_BAD"
    )),
    YES_NO(Map.of(
            1, "YES",
            2, "MID",
            3, "NO"
    ));

    private final Map<Integer, String> mapping;

    SurveyResponseType(Map<Integer, String> mapping) {
        this.mapping = mapping;
    }

    public String getResponse(int key) {
        return mapping.getOrDefault(key, "UNKNOWN");
    }
}