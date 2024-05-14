package com.example.secondtreasurebe.service;

import com.example.secondtreasurebe.model.TopUpTransaction;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public interface TopUpService {
    TopUpTransaction createTopUp(int userId, BigDecimal amount, UUID paymentMethodId);
    List<TopUpTransaction> getAllTopUps();
    void cancelTopUp(UUID topUpId);
    TopUpTransaction getTopUpById(UUID topUpId);
    List<TopUpTransaction> getTopUpByUserId(int userId);
}
