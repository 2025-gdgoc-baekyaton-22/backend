package com.example.gdgoc.user.service;

import com.example.gdgoc.user.domain.Caregiver;
import com.example.gdgoc.user.domain.User;
import com.example.gdgoc.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    private User currentUser;

    public User getCurrentUser(){
        return currentUser;
    }

    public void setCurrentUser(User user){
        this.currentUser = user;
    }

    @Transactional
    public Long saveUser(User user, List<Caregiver> caregivers){
        validateDuplicateUser(user);
        user.setCareGiverList(caregivers);
        userRepository.save(user);
        return user.getId();
    }

    @Transactional
    public Long updateUser(User user){
        user.setLatestUpdateTime(LocalDateTime.now());
        userRepository.save(user);
        return user.getId();
    }

    private void validateDuplicateUser(User user) {
        List<User> findUsers = userRepository.findByCareTakerPhone(user.getCareTakerPhone());
        if(!findUsers.isEmpty())
            throw new IllegalStateException("이미 존재하는 회원입니다.");
    }

    public boolean logIn(String careTakerPhone, String passWord){
        List<User> findUser = userRepository.findByCareTakerPhone(careTakerPhone);
        if(!findUser.isEmpty()){
            if (findUser.get(0).getPassWord().equals(passWord)){
                setCurrentUser(findUser.get(0));
                return true;
            }
        }
        return false;
    }
}
