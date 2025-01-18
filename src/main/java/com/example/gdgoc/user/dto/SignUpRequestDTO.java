package com.example.gdgoc.user.dto;

import lombok.Data;

import java.util.List;

@Data
public class SignUpRequestDTO {
    private String careTakerName;
    private String careTakerAge;
    private String careTakerPhone;
    private String careTakerAddress;
    private String passWord;
    private Boolean threeOrTwo;
    private List<CareGiverDTO> careGiver;
}
