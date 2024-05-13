package com.example.secondtreasurebe.strategy;

import com.example.secondtreasurebe.dto.PaymentMethodRequest;
import com.example.secondtreasurebe.model.CardPaymentDetails;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CardPaymentStrategyTest {

    private CardPaymentStrategy cardPaymentStrategy;

    @BeforeEach
    void setUp() {
        cardPaymentStrategy = new CardPaymentStrategy();
    }

    @Test
    void testProcessPayment() {
        PaymentMethodRequest request = new PaymentMethodRequest();
        request.setCardNumber("1234567812345678");
        request.setCvc("123");
        request.setExpiryDate("12/24");

        CardPaymentDetails cardDetails = (CardPaymentDetails) cardPaymentStrategy.processPayment(request);

        assertNotNull(cardDetails);
        assertEquals("1234567812345678", cardDetails.getCardNumber());
        assertEquals("123", cardDetails.getCvc());
        assertEquals("12/24", cardDetails.getExpiryDate());
    }
}
