package com.example.gdgoc.user.domain;

public enum SurveyStatus {
    YES(1),
    MID(2),
    NO(3),
    VERY_GOOD(4),
    NORMAL(5),
    BAD(6);

    private final int value;

    SurveyStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static SurveyStatus fromValue(int value) {
        for (SurveyStatus status : SurveyStatus.values()) {
            if (status.getValue() == value) {
                return status;
            }
        }
        return null; // or throw IllegalArgumentException
    }
}
