package com.example.secondtreasurebe.controller;

import com.example.secondtreasurebe.dto.PaymentMethodRequest;
import com.example.secondtreasurebe.model.PaymentMethod;
import com.example.secondtreasurebe.service.PaymentMethodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.ArrayList;

@RestController
@CrossOrigin(origins = {"http://localhost:3000/", "https://frontend-adpro-c2.vercel.app/"})
@RequestMapping("/payment-methods")
public class PaymentMethodController {

    @Autowired
    private PaymentMethodService paymentMethodService;

    @PostMapping("/")
    public ResponseEntity<?> createPaymentMethod(@RequestBody PaymentMethodRequest request) {
        try {
            PaymentMethod paymentMethod = paymentMethodService.createPaymentMethod(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(paymentMethod);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error creating payment method: " + e.getMessage());
        }
    }

    @GetMapping("/")
    public ResponseEntity<List<PaymentMethod>> getAllPaymentMethods() {
        List<PaymentMethod> paymentMethods = paymentMethodService.getAllPaymentMethods();
        return ResponseEntity.ok(paymentMethods);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deletePaymentMethod(@PathVariable String id) {
        try {
            paymentMethodService.deletePaymentMethod(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();  // Proper status for a successful deletion without content
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error deleting payment method: " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPaymentMethodById(@PathVariable String id) {
        try {
            PaymentMethod paymentMethod = paymentMethodService.getPaymentMethodById(id);
            return ResponseEntity.ok(paymentMethod);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<PaymentMethod>> getPaymentMethodsByUserId(@PathVariable int userId) {
        try {
            List<PaymentMethod> paymentMethods = paymentMethodService.getPaymentMethodByUserId(userId);
            return ResponseEntity.ok(paymentMethods);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ArrayList<>());}
    }

}
