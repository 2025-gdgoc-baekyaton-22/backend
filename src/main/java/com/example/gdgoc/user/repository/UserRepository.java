package com.example.gdgoc.user.repository;

import com.example.gdgoc.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByUserName(String userName);
}
