package com.example.secondtreasurebe.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "top_up_transaction")
public class TopUpTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @NotNull(message = "User ID cannot be null")
    @Column(name = "user_id", nullable = false)
    private UUID userId;

    @NotNull(message = "Amount cannot be null")
    @Column(name = "amount", nullable = false)
    private BigDecimal amount;

    @NotNull(message = "Payment method ID cannot be null")
    @Column(name = "payment_method_id", nullable = false)
    private UUID paymentMethodId;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Status cannot be null")
    @Column(name = "status", nullable = false)
    private TopUpStatus status; // Enum usage
}
