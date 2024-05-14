package com.example.secondtreasurebe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.secondtreasurebe.model.PaymentMethod;

import java.util.List;
import java.util.Optional;

@Repository
public interface PaymentMethodRepository extends JpaRepository<PaymentMethod, String> {

    Optional<List<PaymentMethod>> findByUserId(Integer userId);
}
