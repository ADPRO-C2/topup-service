package com.example.secondtreasurebe.strategy;

import com.example.secondtreasurebe.dto.PaymentMethodRequest;
import com.example.secondtreasurebe.model.PaymentDetails;

public interface PaymentStrategy {
    PaymentDetails processPayment(PaymentMethodRequest request);
}
