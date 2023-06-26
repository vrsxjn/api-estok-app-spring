package com.example.estok_app.api_estok_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.estok_app.api_estok_app.models.ImageModel;

public interface ImageRepository extends JpaRepository<ImageModel, Long> {
    ImageModel findByFileName(String fileName);
}
