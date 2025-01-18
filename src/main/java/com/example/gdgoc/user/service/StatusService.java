package com.example.gdgoc.user.service;

import com.example.gdgoc.user.domain.Status;
import com.example.gdgoc.user.domain.SurveyStatus;
import com.example.gdgoc.user.repository.StatusRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StatusService {
    private final StatusRepository statusRepository;

    public Long saveStatus(Status status){
        statusRepository.save(status);
        return status.getId();
    }

}
