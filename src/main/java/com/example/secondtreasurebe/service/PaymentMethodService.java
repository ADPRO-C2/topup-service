package com.example.secondtreasurebe.service;

import com.example.secondtreasurebe.dto.PaymentMethodRequest;
import com.example.secondtreasurebe.model.PaymentMethod;

public interface PaymentMethodService {
    PaymentMethod createPaymentMethod(PaymentMethodRequest request);
}
