package com.example.secondtreasurebe.service;

import com.example.secondtreasurebe.dto.PaymentMethodRequest;
import com.example.secondtreasurebe.model.PaymentMethod;
import java.util.List;

public interface PaymentMethodService {
    PaymentMethod createPaymentMethod(PaymentMethodRequest request);
    List<PaymentMethod> getAllPaymentMethods();
    void deletePaymentMethod(String paymentId);
    PaymentMethod getPaymentMethodById(String paymentId);

    List<PaymentMethod> getPaymentMethodByUserId(Integer userId);
}
