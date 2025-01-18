package com.example.gdgoc.user.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Caregiver {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String careGiverName;
    private String careGiverPhone;
    private String careGiverRelationship;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="user_id")
    private User user;

    public Caregiver(){};

    @Builder
    public Caregiver(String careGiverName, String careGiverPhone, String careGiverRelationship) {
        this.careGiverName = careGiverName;
        this.careGiverPhone = careGiverPhone;
        this.careGiverRelationship = careGiverRelationship;
    }
}
