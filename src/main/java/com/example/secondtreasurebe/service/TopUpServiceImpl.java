package com.example.secondtreasurebe.service;

import com.example.secondtreasurebe.model.TopUpStatus;
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
    public TopUpTransaction createTopUp(int userId, BigDecimal amount, UUID paymentMethodId) {
        TopUpTransaction transaction = new TopUpTransaction();
        transaction.setUserId(userId);
        transaction.setAmount(amount);
        transaction.setPaymentMethodId(paymentMethodId);
        transaction.setStatus(TopUpStatus.PENDING); // Set initial status
        return topUpTransactionRepository.save(transaction);
    }

    @Override
    public List<TopUpTransaction> getAllTopUps() {
        return topUpTransactionRepository.findAll();
    }

    @Override
    public void cancelTopUp(UUID topUpId) {
        TopUpTransaction transaction = topUpTransactionRepository.findById(topUpId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid top-up ID: " + topUpId));

        if (transaction.getStatus() == TopUpStatus.PENDING) {
            transaction.setStatus(TopUpStatus.CANCELED);
            topUpTransactionRepository.save(transaction);
        } else {
            throw new IllegalStateException("Top-up is not in a state that can be cancelled.");
        }
    }

    @Override
    public TopUpTransaction getTopUpById(UUID topUpId) {
        return topUpTransactionRepository.findById(topUpId)
                .orElseThrow(() -> new IllegalArgumentException("Top-up transaction not found with ID: " + topUpId));
    }

    @Override
    public List<TopUpTransaction> getTopUpByUserId(int userId) {
        return topUpTransactionRepository.findByUserId(userId)
                .orElseThrow(() -> new IllegalArgumentException("No top-up transactions found for user ID: " + userId));
    }
}
