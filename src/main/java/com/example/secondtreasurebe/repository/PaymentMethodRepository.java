package com.example.secondtreasurebe.repository;

import com.example.secondtreasurebe.model.PaymentMethod;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

@Repository
public class PaymentMethodRepository {

    private final List<PaymentMethod> paymentMethodData = new ArrayList<>();

    public PaymentMethod save(PaymentMethod paymentMethod) {
        if (paymentMethod.getPaymentId() == null) {
            UUID uuid = UUID.randomUUID();
            paymentMethod.setPaymentId(uuid.toString());
        }
        paymentMethodData.add(paymentMethod);
        return paymentMethod;
    }
}
