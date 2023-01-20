package com.eventsystem.repository;

import com.eventsystem.entities.Login;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LoginRepository extends JpaRepository<Login, Integer> {
    Optional<Login> findLoginByEmail(String email);
}
