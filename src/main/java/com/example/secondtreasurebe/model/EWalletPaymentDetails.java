package com.example.secondtreasurebe.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
@Table(name = "ewallet_payment_details")
public class EWalletPaymentDetails extends PaymentDetails {
    @NotBlank(message = "Phone number cannot be blank")
    private String phoneNumber;
}
