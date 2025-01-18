package com.example.gdgoc.user.domain;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Status {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private LocalDateTime surveyTime;

    private Integer survery1;
    private Integer survery2;
    private Integer survery3;
    private Integer survery4;
    private Integer survery5;
    private Integer survery6;
    private Integer survery7;
    private Integer survery8;
    private Integer survery9;
    private Integer survery10;
    private Integer survery11;
    private Integer survery12;
    private Integer survery13;
    private Integer survery14;
    private Integer survery15;


}
