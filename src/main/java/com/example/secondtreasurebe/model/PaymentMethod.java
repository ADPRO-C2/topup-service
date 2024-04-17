package com.example.secondtreasurebe.model;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PaymentMethod {
    private String paymentId;
    private String paymentType;  // "card" atau "e-wallet"
    private PaymentDetails paymentDetails;
}
