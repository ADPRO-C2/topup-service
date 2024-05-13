package com.example.secondtreasurebe.strategy;

import com.example.secondtreasurebe.dto.PaymentMethodRequest;
import com.example.secondtreasurebe.model.EWalletPaymentDetails;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EWalletPaymentStrategyTest {

    private EWalletPaymentStrategy eWalletPaymentStrategy;

    @BeforeEach
    void setUp() {
        eWalletPaymentStrategy = new EWalletPaymentStrategy();
    }

    @Test
    void testProcessPayment() {
        PaymentMethodRequest request = new PaymentMethodRequest();
        request.setPhoneNumber("081234567890");

        EWalletPaymentDetails eWalletDetails = (EWalletPaymentDetails) eWalletPaymentStrategy.processPayment(request);

        assertNotNull(eWalletDetails);
        assertEquals("081234567890", eWalletDetails.getPhoneNumber());
    }
}
