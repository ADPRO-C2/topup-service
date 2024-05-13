package com.example.secondtreasurebe.controller;

import com.example.secondtreasurebe.dto.PaymentMethodRequest;
import com.example.secondtreasurebe.model.PaymentMethod;
import com.example.secondtreasurebe.service.PaymentMethodService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PaymentMethodControllerTest {

    @Mock
    private PaymentMethodService paymentMethodService;

    @InjectMocks
    private PaymentMethodController paymentMethodController;

    private PaymentMethod paymentMethod;

    @BeforeEach
    void setUp() {
        paymentMethod = new PaymentMethod();
        paymentMethod.setPaymentId("testId");
        paymentMethod.setPaymentType("card");
    }

    @Test
    void testCreatePaymentMethod() {
        PaymentMethodRequest request = new PaymentMethodRequest();
        request.setPaymentType("card");
        when(paymentMethodService.createPaymentMethod(request)).thenReturn(paymentMethod);

        ResponseEntity<PaymentMethod> response = paymentMethodController.createPaymentMethod(request);

        verify(paymentMethodService, times(1)).createPaymentMethod(request);
        assertNotNull(response);
        assertEquals(paymentMethod, response.getBody());
    }

    @Test
    void testGetAllPaymentMethods() {
        when(paymentMethodService.getAllPaymentMethods()).thenReturn(Arrays.asList(paymentMethod));

        ResponseEntity<List<PaymentMethod>> response = paymentMethodController.getAllPaymentMethods();

        verify(paymentMethodService, times(1)).getAllPaymentMethods();
        assertNotNull(response);
        assertFalse(response.getBody().isEmpty());
        assertEquals(1, response.getBody().size());
        assertEquals(paymentMethod, response.getBody().get(0));
    }

    @Test
    void testDeletePaymentMethod() {
        doNothing().when(paymentMethodService).deletePaymentMethod("testId");

        ResponseEntity<Void> response = paymentMethodController.deletePaymentMethod("testId");

        verify(paymentMethodService, times(1)).deletePaymentMethod("testId");
        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
    }
}