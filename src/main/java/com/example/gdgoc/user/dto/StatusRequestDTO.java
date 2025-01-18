package com.example.gdgoc.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StatusRequestDTO {
    private Integer dayTime;
    private Long userUrlId;
    private Integer currentBodyStatus;
    private Integer feeling;
    private Integer communicate;
    private Integer sleep;
    private Integer meal;
    private Integer medication;
    private Integer hospital;
    private Integer memory;
    private Integer defecation;
}
