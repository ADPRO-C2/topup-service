package com.example.secondtreasurebe.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PaymentMethodRequest {
    private String paymentType;
    private String cardNumber;
    private String cvc;
    private String expiryDate;
    private String phoneNumber;
}
