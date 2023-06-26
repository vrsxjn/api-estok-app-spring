package com.example.estok_app.api_estok_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.estok_app.api_estok_app.models.LoginModel;

@Repository
public interface UserRepository extends JpaRepository<LoginModel, Long> {
    LoginModel findByToken(String token);
    LoginModel findByEmail(String email);
}