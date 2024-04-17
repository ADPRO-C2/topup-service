package com.example.secondtreasurebe.service;

import com.example.secondtreasurebe.model.PaymentMethod;

public interface PaymentMethodService {
    PaymentMethod createPaymentMethod(String paymentType, String cardNumber, String cvc, String expiryDate, String phoneNumber);
}
