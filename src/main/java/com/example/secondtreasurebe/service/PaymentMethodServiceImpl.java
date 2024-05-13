package com.example.secondtreasurebe.service;

import com.example.secondtreasurebe.dto.PaymentMethodRequest;
import com.example.secondtreasurebe.model.PaymentMethod;
import com.example.secondtreasurebe.repository.PaymentMethodRepository;
import com.example.secondtreasurebe.strategy.CardPaymentStrategy;
import com.example.secondtreasurebe.strategy.EWalletPaymentStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PaymentMethodServiceImpl implements PaymentMethodService {

    @Autowired
    private PaymentMethodRepository paymentMethodRepository;

    @Autowired
    private CardPaymentStrategy cardPaymentStrategy;

    @Autowired
    private EWalletPaymentStrategy eWalletPaymentStrategy;

    @Override
    public PaymentMethod createPaymentMethod(PaymentMethodRequest request) {
        PaymentMethod paymentMethod = new PaymentMethod();
        paymentMethod.setPaymentId(UUID.randomUUID().toString());
        paymentMethod.setPaymentType(request.getPaymentType());

        switch (request.getPaymentType().toLowerCase()) {
            case "card":
                paymentMethod.setPaymentDetails(cardPaymentStrategy.processPayment(request));
                break;
            case "e-wallet":
                paymentMethod.setPaymentDetails(eWalletPaymentStrategy.processPayment(request));
                break;
            default:
                throw new IllegalArgumentException("Unsupported payment type: " + request.getPaymentType());
        }

        return paymentMethodRepository.save(paymentMethod);
    }

    @Override
    public List<PaymentMethod> getAllPaymentMethods() {
        return paymentMethodRepository.findAll();
    }

    @Override
    public void deletePaymentMethod(String paymentId) {
        paymentMethodRepository.deleteById(paymentId);
    }
}
