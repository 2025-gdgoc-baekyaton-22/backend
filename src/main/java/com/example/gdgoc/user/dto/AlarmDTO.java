package com.example.gdgoc.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AlarmDTO {
    boolean threeOrTwo;
    String careTakerPhone;
    String careGiverPhone;
}
