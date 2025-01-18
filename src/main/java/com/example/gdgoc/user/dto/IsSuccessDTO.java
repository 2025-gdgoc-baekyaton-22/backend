package com.example.gdgoc.user.dto;

import lombok.Data;

@Data
public class IsSuccessDTO {
    private Boolean isSuccess;

    public IsSuccessDTO(Boolean isSuccess){
        this.isSuccess = isSuccess;
    }
}
