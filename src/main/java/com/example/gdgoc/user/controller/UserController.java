package com.example.gdgoc.user.controller;

import com.example.gdgoc.user.domain.Caregiver;
import com.example.gdgoc.user.domain.User;
import com.example.gdgoc.user.dto.*;
import com.example.gdgoc.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
@Slf4j
public class UserController {
    private final UserService userService;

    @PostMapping("/signin")
    public IsSuccessDTO signIn(@RequestBody SignInRequestDTO dto) {
        log.info(dto.getCareTakerPhone());
        return new IsSuccessDTO(userService.signIn(dto.getCareTakerPhone(), dto.getPassWord()));
    }

    @PostMapping("/signup")
    public IsSuccessDTO signUp(@RequestBody SignUpRequestDTO dto) {
        System.out.println(dto.getThreeOrTwo());
        User user = User.builder()
                .careTakerName(dto.getCareTakerName())
                .careTakerAge(dto.getCareTakerAge())
                .careTakerPhone(dto.getCareTakerPhone())
                .careTakerAddress(dto.getCareTakerAddress())
                .passWord(dto.getPassWord())
                .threeOrTwo(dto.getThreeOrTwo())
                .build();
        for (CareGiverDTO careGiverDTO : dto.getCareGiver()) {
            Caregiver caregiver = Caregiver.builder()
                    .careGiverName(careGiverDTO.getCareGiverName())
                    .careGiverPhone(careGiverDTO.getCareGiverPhone())
                    .careGiverRelationship(careGiverDTO.getCareGiverRelationship())
                    .build();
            user.addCareGiver(caregiver);
        }
        if (userService.saveUser(user)) {
            return new IsSuccessDTO(true);
        } else {
            return new IsSuccessDTO(false);
        }
    }

    @GetMapping("/startpage")
    public ResponseEntity<StartPageResponseDTO> startpage(@RequestParam(value = "userId") Long userId) {
        String name = userService.findUserName(userId);
        StartPageResponseDTO startPageResponseDTO = StartPageResponseDTO.builder()
                .name(name)
                .build();
        return ResponseEntity.ok(startPageResponseDTO);
    }

    @GetMapping("/info")
    public DashBoardRespondDTO dashboard(){
        User user = userService.getCurrentUser();
        DashBoardRespondDTO dashBoardRespondDTO = new DashBoardRespondDTO();
        dashBoardRespondDTO.setCareTakerName(user.getCareTakerName());
        dashBoardRespondDTO.setCareTakerAge(user.getCareTakerAge());
        System.out.println(user.getStatusList().size());

        LocalDateTime localDateTime = user.getStatusList().get(user.getStatusList().size()-1).getSurveyTime();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String formattedDate = localDateTime.format(formatter);
        dashBoardRespondDTO.setLatestUpdate(formattedDate);

        String report = user.getStatusList().get(user.getStatusList().size()-1).getProtectorReport();
        String cleanedReport = report.replace("\n", "").replace("*", "");
        dashBoardRespondDTO.setSignificant(cleanedReport);

        for(Caregiver caregiver: user.getCareGiverList()){
            CareGiverNameDTO careGiverNameDTO = new CareGiverNameDTO();
            careGiverNameDTO.setCareGiverName(caregiver.getCareGiverName());
            dashBoardRespondDTO.getCareGivers().add(careGiverNameDTO);
        }
        return dashBoardRespondDTO;
    }
}
