package com.example.secondtreasurebe.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class TopUpTransactionTest {

    private TopUpTransaction topUpTransaction;

    @BeforeEach
    void setUp() {
        topUpTransaction = new TopUpTransaction();
    }

    @Test
    void testGettersAndSetters() {
        UUID id = UUID.randomUUID();
        UUID userId = UUID.randomUUID();
        BigDecimal amount = BigDecimal.valueOf(1000);
        UUID paymentMethodId = UUID.randomUUID();
        TopUpStatus status = TopUpStatus.PENDING; // Use enum instead of String

        topUpTransaction.setId(id);
        topUpTransaction.setUserId(userId);
        topUpTransaction.setAmount(amount);
        topUpTransaction.setPaymentMethodId(paymentMethodId);
        topUpTransaction.setStatus(status); // Set status using enum

        assertEquals(id, topUpTransaction.getId());
        assertEquals(userId, topUpTransaction.getUserId());
        assertEquals(amount, topUpTransaction.getAmount());
        assertEquals(paymentMethodId, topUpTransaction.getPaymentMethodId());
        assertEquals(status, topUpTransaction.getStatus());
    }

    @Test
    void testNoArgsConstructor() {
        TopUpTransaction transaction = new TopUpTransaction();
        assertNotNull(transaction);
    }
}
