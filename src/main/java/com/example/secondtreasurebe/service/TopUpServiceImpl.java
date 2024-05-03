package com.example.secondtreasurebe.service;

import com.example.secondtreasurebe.model.TopUpTransaction;
import com.example.secondtreasurebe.repository.TopUpTransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
public class TopUpServiceImpl implements TopUpService {

    @Autowired
    private TopUpTransactionRepository topUpTransactionRepository;

    @Override
    public TopUpTransaction createTopUp(UUID userId, BigDecimal amount, UUID paymentMethodId) {
        TopUpTransaction transaction = new TopUpTransaction();
        transaction.setUserId(userId);
        transaction.setAmount(amount);
        transaction.setPaymentMethodId(paymentMethodId);
        transaction.setStatus("pending");
        return topUpTransactionRepository.save(transaction);
    }

    @Override
    public List<TopUpTransaction> getAllTopUps() {
        return topUpTransactionRepository.findAll();
    }

    @Override
    public void cancelTopUp(UUID topUpId) {
        TopUpTransaction transaction = topUpTransactionRepository.findById(topUpId).orElseThrow(() -> new IllegalArgumentException("Invalid top-up ID: " + topUpId));
        if ("pending".equals(transaction.getStatus())) {
            transaction.setStatus("cancelled");
            topUpTransactionRepository.save(transaction);
        } else {
            throw new IllegalStateException("Top-up is not in a state that can be cancelled.");
        }
    }
}
