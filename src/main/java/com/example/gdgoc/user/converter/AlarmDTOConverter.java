package com.example.gdgoc.user.converter;

import com.example.gdgoc.user.domain.User;
import com.example.gdgoc.user.dto.AlarmDTO;

public class AlarmDTOConverter {

    public static AlarmDTO toalarmDTO(User user) {
        return AlarmDTO.builder()
                .threeOrTwo(user.getThreeOrTwo())
                .careTakerPhone(user.getCareTakerPhone())
                .careGiverPhone(user.getCareGiverList().get(0).getCareGiverPhone())
                .build();
    }
}
