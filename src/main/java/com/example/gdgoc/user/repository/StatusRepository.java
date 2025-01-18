package com.example.gdgoc.user.repository;

import com.example.gdgoc.user.domain.Status;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusRepository extends JpaRepository<Status, Long> {
}
