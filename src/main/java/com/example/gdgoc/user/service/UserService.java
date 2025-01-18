package com.example.gdgoc.user.service;

import com.example.gdgoc.user.domain.Caregiver;
import com.example.gdgoc.user.domain.Status;
import com.example.gdgoc.user.domain.User;
import com.example.gdgoc.user.repository.StatusRepository;
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
    private final StatusRepository statusRepository;

    private User currentUser;

    public User getCurrentUser(){
        return currentUser;
    }

    public void setCurrentUser(User user){
        this.currentUser = user;
    }

    @Transactional
    public Boolean saveUser(User user){
        if(validateDuplicateUser(user)) {
            userRepository.save(user);
            return true;
        }
        else
            return false;
    }

    @Transactional
    public Long updateUser(User user){
        user.setLatestUpdateTime(LocalDateTime.now());
        userRepository.save(user);
        return user.getId();
    }

    private Boolean validateDuplicateUser(User user) {
        List<User> findUsers = userRepository.findByCareTakerPhone(user.getCareTakerPhone());
        if(!findUsers.isEmpty())
            return false;
        else
            return true;
    }

    public boolean signIn(String careTakerPhone, String passWord){
        List<User> findUser = userRepository.findByCareTakerPhone(careTakerPhone);
        if(!findUser.isEmpty()){
            if (findUser.get(0).getPassWord().equals(passWord)){
                setCurrentUser(findUser.get(0));
                return true;
            }
        }
        return false;
    }

    public void saveStatus(Status status){
        statusRepository.save(status);
    }
}
