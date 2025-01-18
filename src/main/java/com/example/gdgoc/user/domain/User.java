package com.example.gdgoc.user.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private String userName;
    private String passWord;
    private String caregiver_phone;
    private String caretaker_phone;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Status> statusList = new ArrayList<>();

    public User(){}

    @Builder
    public User(String userName, String passWord, String caregiver_phone, String caretaker_phone) {
        this.userName = userName;
        this.passWord = passWord;
        this.caregiver_phone = caregiver_phone;
        this.caretaker_phone = caretaker_phone;
    }

    public static User signUpUser(String userName, String passWord, String caregiver_phone, String caretaker_phone){
        return User.builder()
                .userName(userName)
                .passWord(passWord)
                .caregiver_phone(caregiver_phone)
                .caretaker_phone(caretaker_phone)
                .build();
    }
}
