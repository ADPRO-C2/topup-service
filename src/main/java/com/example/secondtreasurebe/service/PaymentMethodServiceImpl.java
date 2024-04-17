package com.example.secondtreasurebe.service;

import com.example.secondtreasurebe.factory.PaymentDetailsFactory;
import com.example.secondtreasurebe.model.PaymentMethod;
import com.example.secondtreasurebe.repository.PaymentMethodRepository;
import com.example.secondtreasurebe.service.PaymentMethodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentMethodServiceImpl implements PaymentMethodService {
    private final PaymentDetailsFactory paymentDetailsFactory;
    private final PaymentMethodRepository paymentMethodRepository;

    @Autowired
    public PaymentMethodServiceImpl(PaymentDetailsFactory paymentDetailsFactory, PaymentMethodRepository paymentMethodRepository) {
        this.paymentDetailsFactory = paymentDetailsFactory;
        this.paymentMethodRepository = paymentMethodRepository;
    }

    @Override
    public PaymentMethod createPaymentMethod(String paymentType, String cardNumber, String cvc, String expiryDate, String phoneNumber) {
        PaymentMethod newPaymentMethod = new PaymentMethod();
        newPaymentMethod.setPaymentType(paymentType);
        if ("card".equalsIgnoreCase(paymentType)) {
            newPaymentMethod.setPaymentDetails(paymentDetailsFactory.createCardPaymentDetails(cardNumber, cvc, expiryDate));
        } else if ("e-wallet".equalsIgnoreCase(paymentType)) {
            newPaymentMethod.setPaymentDetails(paymentDetailsFactory.createEWalletPaymentDetails(phoneNumber));
        } else {
            throw new IllegalArgumentException("Unsupported payment type: " + paymentType);
        }
        paymentMethodRepository.save(newPaymentMethod);
        return newPaymentMethod;
    }
}
