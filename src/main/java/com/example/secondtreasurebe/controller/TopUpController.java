package com.example.secondtreasurebe.controller;

import com.example.secondtreasurebe.model.TopUpTransaction;
import com.example.secondtreasurebe.service.TopUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "http://34.143.169.241")
@RequestMapping("/topups")
public class TopUpController {

    @Autowired
    private TopUpService topUpService;

    @PostMapping("/")
    public ResponseEntity<?> createTopUp(@RequestBody TopUpRequest topUpRequest) {
        try {
            TopUpTransaction topUp = topUpService.createTopUp(topUpRequest.getUserId(), topUpRequest.getAmount(), topUpRequest.getPaymentMethodId());
            return ResponseEntity.status(HttpStatus.CREATED).body(topUp);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error creating top-up: " + e.getMessage());
        }
    }

    @GetMapping("/")
    public ResponseEntity<List<TopUpTransaction>> getAllTopUps() {
        List<TopUpTransaction> topUps = topUpService.getAllTopUps();
        return ResponseEntity.ok(topUps);
    }

    @PatchMapping("/{topUpId}/cancel")
    public ResponseEntity<?> cancelTopUp(@PathVariable UUID topUpId) {
        try {
            topUpService.cancelTopUp(topUpId);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Error cancelling top-up: " + e.getMessage());
        }
    }

    @GetMapping("/{topUpId}")
    public ResponseEntity<?> getTopUpById(@PathVariable UUID topUpId) {
        try {
            TopUpTransaction topUp = topUpService.getTopUpById(topUpId);
            return ResponseEntity.ok(topUp);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("user/{userId}")
    public ResponseEntity<List<TopUpTransaction>> getTopUpByUserId(@PathVariable int userId) {
        try {
            List<TopUpTransaction> topUps = topUpService.getTopUpByUserId(userId);
            return ResponseEntity.ok(topUps);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ArrayList<>());
            }
    }


    // Helper DTO
    static class TopUpRequest {
        private int userId; // Changed from UUID to int
        private BigDecimal amount;
        private UUID paymentMethodId;

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
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
