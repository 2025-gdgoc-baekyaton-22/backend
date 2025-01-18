package com.example.gdgoc.user.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private String passWord;
    private String careTakerPhone;
    private String careTakerName;
    private String careTakerAge;
    private String careTakerAddress;


    @ElementCollection
    @CollectionTable(name = "user_caregiver", joinColumns = @JoinColumn(name = "user_id"))
    private List<Caregiver> careGiverList = new ArrayList<>();

    private LocalDateTime latestUpdateTime;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<Status> statusList = new ArrayList<>();

    private Boolean threeOrTwo; // true : 하루 3회 / false : 하루 2회

    public User(){}

    @Builder
    public User(String careTakerPhone, String careTakerName, String careTakerAge, String careTakerAddress, String passWord, Boolean threeOrTwo) {
        this.careTakerPhone = careTakerPhone;
        this.careTakerName = careTakerName;
        this.careTakerAge = careTakerAge;
        this.careTakerAddress = careTakerAddress;
        this.passWord = passWord;
        this.threeOrTwo = threeOrTwo;
    }

    public void addCareGiver(Caregiver caregiver){
        this.careGiverList.add(caregiver);
    }

    public void addStatus(Status status){
        this.statusList.add(status);
    }
}
