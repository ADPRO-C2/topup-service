package com.example.secondtreasurebe.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EWalletPaymentDetailsTest {

    private EWalletPaymentDetails eWalletDetails;

    @BeforeEach
    void setUp() {
        eWalletDetails = new EWalletPaymentDetails();
        eWalletDetails.setPhoneNumber("081234567890");
    }

    @Test
    void testGetPhoneNumber() {
        assertEquals("081234567890", eWalletDetails.getPhoneNumber());
    }

    @Test
    void testSetPhoneNumber() {
        eWalletDetails.setPhoneNumber("089876543210");
        assertEquals("089876543210", eWalletDetails.getPhoneNumber());
    }
}
