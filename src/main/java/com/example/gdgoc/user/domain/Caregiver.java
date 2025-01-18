package com.example.gdgoc.user.domain;

import jakarta.persistence.Embeddable;
import lombok.Builder;
import lombok.Data;

@Data
@Embeddable
public class Caregiver {
    private String careGiverName;
    private String careGiverPhone;
    private String careGiverRelationship;

    public Caregiver(){};

    @Builder
    public Caregiver(String careGiverName, String careGiverPhone, String careGiverRelationship) {
        this.careGiverName = careGiverName;
        this.careGiverPhone = careGiverPhone;
        this.careGiverRelationship = careGiverRelationship;
    }
}
