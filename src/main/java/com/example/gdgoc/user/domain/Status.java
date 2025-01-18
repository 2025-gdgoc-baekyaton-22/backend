package com.example.gdgoc.user.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter @Setter
public class Status {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    // 1: 아침 2: 점심 3: 저녁
    private Integer dayTime;

    // 최근 업데이트 시간
    private LocalDateTime surveyTime;

    // 1: 네 2: 보통 3: 아니요
    // 4: 아주 괜찮아요 5: 그저 그래요 6: 아주 안좋아요
    private SurveyStatus currentBodyStatus; // 현재 몸 상태
    private SurveyStatus defecation; // 배변, 배뇨
    private SurveyStatus feeling; // 기분
    private SurveyStatus communicate; // 친구, 가족과의 만남
    private SurveyStatus medication; // 약 복용 여부
    private SurveyStatus meal; // 식사
    private SurveyStatus sleep; // 수면 질
    private SurveyStatus memory; // 건망증, 치매 여부
    private SurveyStatus hospital; // 통원 여부

    public Status(){}

    @Builder
    public Status(Integer dayTime, SurveyStatus currentBodyStatus, SurveyStatus defecation, SurveyStatus feeling, SurveyStatus communicate, SurveyStatus medication, SurveyStatus meal, SurveyStatus sleep, SurveyStatus memory, SurveyStatus hospital) {
        this.dayTime = dayTime;
        this.surveyTime = LocalDateTime.now();
        this.currentBodyStatus = currentBodyStatus;
        this.defecation = defecation;
        this.feeling = feeling;
        this.communicate = communicate;
        this.medication = medication;
        this.meal = meal;
        this.sleep = sleep;
        this.memory = memory;
        this.hospital = hospital;
    }


    public void setUser(User user){
        this.user = user;
        user.addStatus(this);
    }

}
