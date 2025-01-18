package com.example.gdgoc.user.domain;

import jakarta.persistence.*;
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

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Status> statusList = new ArrayList<>();

    public User(){}

    public void addStatus(Status status){
        this.statusList.add(status);
    }
}
