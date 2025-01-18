package com.example.gdgoc.user.domain;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class Caregiver {
    private String careGiverName;
    private String careGiverPhone;
    private String careGiverRelationship;
}
