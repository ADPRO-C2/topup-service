package com.example.secondtreasurebe.service;

import com.example.secondtreasurebe.dto.PaymentMethodRequest;
import com.example.secondtreasurebe.factory.PaymentDetailsFactory;
import com.example.secondtreasurebe.model.PaymentMethod;
import com.example.secondtreasurebe.repository.PaymentMethodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

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
    public PaymentMethod createPaymentMethod(PaymentMethodRequest request) {
        PaymentMethod paymentMethod = new PaymentMethod();
        paymentMethod.setPaymentType(request.getPaymentType());
        if ("card".equalsIgnoreCase(request.getPaymentType())) {
            paymentMethod.setPaymentDetails(paymentDetailsFactory.createCardPaymentDetails(
                    request.getCardNumber(), request.getCvc(), request.getExpiryDate()));
        } else if ("e-wallet".equalsIgnoreCase(request.getPaymentType())) {
            paymentMethod.setPaymentDetails(paymentDetailsFactory.createEWalletPaymentDetails(
                    request.getPhoneNumber()));
        } else {
            throw new IllegalArgumentException("Unsupported payment type: " + request.getPaymentType());
        }
        paymentMethodRepository.save(paymentMethod);
        return paymentMethod;
    }
    @Override
    public List<PaymentMethod> getAllPaymentMethods() {
        return paymentMethodRepository.findAll();  // Assuming you have or will implement this method
    }

}
