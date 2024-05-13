package com.example.secondtreasurebe.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CardPaymentDetailsTest {

    private CardPaymentDetails cardDetails;

    @BeforeEach
    void setUp() {
        cardDetails = new CardPaymentDetails();
        cardDetails.setCardNumber("1234567812345678");
        cardDetails.setCvc("123");
        cardDetails.setExpiryDate("12/24");
    }

    @Test
    void testGetCardNumber() {
        assertEquals("1234567812345678", cardDetails.getCardNumber());
    }

    @Test
    void testGetCvc() {
        assertEquals("123", cardDetails.getCvc());
    }

    @Test
    void testGetExpiryDate() {
        assertEquals("12/24", cardDetails.getExpiryDate());
    }

    @Test
    void testSetCardNumber() {
        cardDetails.setCardNumber("8765432187654321");
        assertEquals("8765432187654321", cardDetails.getCardNumber());
    }

    @Test
    void testSetCvc() {
        cardDetails.setCvc("321");
        assertEquals("321", cardDetails.getCvc());
    }

    @Test
    void testSetExpiryDate() {
        cardDetails.setExpiryDate("01/25");
        assertEquals("01/25", cardDetails.getExpiryDate());
    }
}
