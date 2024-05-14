package com.example.secondtreasurebe.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PaymentModelTest {

    private PaymentMethod paymentMethod;
    private CardPaymentDetails cardDetails;

    @BeforeEach
    void setUp() {
        cardDetails = new CardPaymentDetails();
        cardDetails.setCardNumber("1234567812345678");
        cardDetails.setCvc("123");
        cardDetails.setExpiryDate("12/24");

        paymentMethod = new PaymentMethod();
        paymentMethod.setPaymentId("testId");
        paymentMethod.setPaymentType("card");
        paymentMethod.setPaymentDetails(cardDetails);
        paymentMethod.setUserId(1);
    }

    @Test
    void testGetPaymentId() {
        assertEquals("testId", paymentMethod.getPaymentId());
    }

    @Test
    void testGetPaymentType() {
        assertEquals("card", paymentMethod.getPaymentType());
    }

    @Test
    void testGetPaymentDetails() {
        assertEquals(cardDetails, paymentMethod.getPaymentDetails());
    }

    @Test
    void testGetUserId() {
        assertEquals(1, paymentMethod.getUserId());
    }

    @Test
    void testSetPaymentId() {
        paymentMethod.setPaymentId("newId");
        assertEquals("newId", paymentMethod.getPaymentId());
    }

    @Test
    void testSetPaymentType() {
        paymentMethod.setPaymentType("e-wallet");
        assertEquals("e-wallet", paymentMethod.getPaymentType());
    }

    @Test
    void testSetPaymentDetails() {
        EWalletPaymentDetails eWalletDetails = new EWalletPaymentDetails();
        eWalletDetails.setPhoneNumber("081234567890");
        paymentMethod.setPaymentDetails(eWalletDetails);
        assertEquals(eWalletDetails, paymentMethod.getPaymentDetails());
    }

    @Test
    void testSetUserId() {
        paymentMethod.setUserId(2);
        assertEquals(2, paymentMethod.getUserId());
    }

}
