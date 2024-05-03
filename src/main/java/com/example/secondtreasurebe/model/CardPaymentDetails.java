package com.example.secondtreasurebe.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
@Table(name = "card_payment_details")
public class CardPaymentDetails extends PaymentDetails {
    @NotBlank(message = "Card number cannot be blank")
    @Size(min = 16, max = 16, message = "Card number must be 16 digits")
    private String cardNumber;

    @NotBlank(message = "CVC cannot be blank")
    @Size(min = 3, max = 4, message = "CVC must be 3 or 4 digits")
    private String cvc;

    @NotBlank(message = "Expiry date cannot be blank")
    private String expiryDate;
}
