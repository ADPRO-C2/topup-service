package com.example.secondtreasurebe.controller;

import com.example.secondtreasurebe.model.TopUpTransaction;
import com.example.secondtreasurebe.service.TopUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/topups")
public class TopUpController {

    @Autowired
    private TopUpService topUpService;

    @PostMapping
    public TopUpTransaction createTopUp(@RequestBody TopUpRequest topUpRequest) {
        return topUpService.createTopUp(topUpRequest.getUserId(), topUpRequest.getAmount(), topUpRequest.getPaymentMethodId());
    }

    @GetMapping
    public List<TopUpTransaction> getAllTopUps() {
        return topUpService.getAllTopUps();
    }

    @PatchMapping("/{topUpId}/cancel")
    public void cancelTopUp(@PathVariable UUID topUpId) {
        topUpService.cancelTopUp(topUpId);
    }

    // Helper DTO
    static class TopUpRequest {
        private UUID userId;
        private BigDecimal amount;
        private UUID paymentMethodId;

        public UUID getUserId() {
            return userId;
        }

        public void setUserId(UUID userId) {
            this.userId = userId;
        }

        public BigDecimal getAmount() {
            return amount;
        }

        public void setAmount(BigDecimal amount) {
            this.amount = amount;
        }

        public UUID getPaymentMethodId() {
            return paymentMethodId;
        }

        public void setPaymentMethodId(UUID paymentMethodId) {
            this.paymentMethodId = paymentMethodId;
        }
    }
}
