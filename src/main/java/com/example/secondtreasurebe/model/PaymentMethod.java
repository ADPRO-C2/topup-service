package com.example.secondtreasurebe.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;


@Getter @Setter
@Entity
@Table(name = "payment_method")
public class PaymentMethod {
    @Id
    @Column(name = "payment_id", updatable = false, nullable = false)
    private String paymentId;

    @NotNull(message = "Payment type cannot be null")
    @Column(name = "payment_type", nullable = false)
    private String paymentType;  // "card" or "e-wallet"

    @NotNull(message = "Payment details cannot be null")
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "payment_details_id", referencedColumnName = "id")
    private PaymentDetails paymentDetails;
}
