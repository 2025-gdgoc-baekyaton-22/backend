package com.example.gdgoc.user.dto;

import lombok.Data;

@Data
public class StatusRequestDTO {
    private Integer dayTime;
    private Integer currentBodyStatus;
    private Integer feeling;
    private Integer communicate;
    private Integer sleep;
    private Integer meal;
    private Integer medication;
    private Integer hospital;
    private Integer memory;
}
