package com.example.secondtreasurebe.model;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CardPaymentDetails extends PaymentDetails {
    private String cardNumber;
    private String cvc;
    private String expiryDate;
}
