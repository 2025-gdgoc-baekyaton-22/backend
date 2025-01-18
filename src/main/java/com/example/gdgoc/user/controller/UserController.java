package com.example.gdgoc.user.controller;

import com.example.gdgoc.user.domain.Caregiver;
import com.example.gdgoc.user.domain.User;
import com.example.gdgoc.user.dto.CareGiverDTO;
import com.example.gdgoc.user.dto.IsSuccessDTO;
import com.example.gdgoc.user.dto.SignInRequestDTO;
import com.example.gdgoc.user.dto.SignUpRequestDTO;
import com.example.gdgoc.user.dto.StartPageResponseDTO;
import com.example.gdgoc.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @PostMapping("/signin")
    public IsSuccessDTO signIn(@RequestBody SignInRequestDTO dto) {
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
}
