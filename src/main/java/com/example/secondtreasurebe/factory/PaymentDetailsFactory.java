package com.example.secondtreasurebe.factory;

import com.example.secondtreasurebe.model.PaymentDetails;

public interface PaymentDetailsFactory {
    PaymentDetails createCardPaymentDetails(String cardNumber, String cvc, String expiryDate);
    PaymentDetails createEWalletPaymentDetails(String phoneNumber);
}
