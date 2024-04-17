package com.example.secondtreasurebe.controller;

import com.example.secondtreasurebe.model.PaymentMethod;
import com.example.secondtreasurebe.service.PaymentMethodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payment-methods")
public class PaymentMethodController {

    @Autowired
    private PaymentMethodService paymentMethodService;

    @PostMapping("/")
    public ResponseEntity<PaymentMethod> createPaymentMethod(@RequestParam String paymentType,
                                                             @RequestParam(required = false) String cardNumber,
                                                             @RequestParam(required = false) String cvc,
                                                             @RequestParam(required = false) String expiryDate,
                                                             @RequestParam(required = false) String phoneNumber) {
        PaymentMethod paymentMethod = paymentMethodService.createPaymentMethod(paymentType, cardNumber, cvc, expiryDate, phoneNumber);
        return ResponseEntity.ok(paymentMethod);
    }
}
