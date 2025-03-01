package com.example.secondtreasurebe.repository;

import com.example.secondtreasurebe.model.TopUpTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface TopUpTransactionRepository extends JpaRepository<TopUpTransaction, UUID> {
    Optional<List<TopUpTransaction>> findByUserId(int userId);
}
