package com.example.estok_app.api_estok_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.estok_app.api_estok_app.models.StockModel;

@Repository
public interface StockRepository extends JpaRepository<StockModel, Long> {

}