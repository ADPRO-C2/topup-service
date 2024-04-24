package com.example.secondtreasurebe.controller;

import com.example.secondtreasurebe.dto.PaymentMethodRequest;
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
    public ResponseEntity<PaymentMethod> createPaymentMethod(@RequestBody PaymentMethodRequest request) {
        PaymentMethod paymentMethod = paymentMethodService.createPaymentMethod(request);
        return ResponseEntity.ok(paymentMethod);
    }
}
