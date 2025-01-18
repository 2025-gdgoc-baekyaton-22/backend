package com.example.gdgoc.user.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class DashBoardRespondDTO {
    private String careTakerName;
    private String careTakerAge;
    private List<CareGiverNameDTO> careGivers = new ArrayList<>();
    private String latestUpdate;
    private String significant;
}
