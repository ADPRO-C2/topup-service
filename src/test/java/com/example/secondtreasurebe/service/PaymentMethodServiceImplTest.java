package com.example.secondtreasurebe.service;

import com.example.secondtreasurebe.dto.PaymentMethodRequest;
import com.example.secondtreasurebe.model.PaymentDetails;
import com.example.secondtreasurebe.model.PaymentMethod;
import com.example.secondtreasurebe.repository.PaymentMethodRepository;
import com.example.secondtreasurebe.strategy.CardPaymentStrategy;
import com.example.secondtreasurebe.strategy.EWalletPaymentStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PaymentMethodServiceImplTest {

    @Mock
    private PaymentMethodRepository paymentMethodRepository;

    @Mock
    private CardPaymentStrategy cardPaymentStrategy;

    @Mock
    private EWalletPaymentStrategy eWalletPaymentStrategy;

    @InjectMocks
    private PaymentMethodServiceImpl paymentMethodService;

    private PaymentMethodRequest cardRequest;
    private PaymentMethodRequest eWalletRequest;
    private PaymentDetails cardDetails;
    private PaymentDetails eWalletDetails;

    @BeforeEach
    void setUp() {
        cardRequest = new PaymentMethodRequest();
        cardRequest.setPaymentType("card");
        cardRequest.setCardNumber("1234567812345678");
        cardRequest.setCvc("123");
        cardRequest.setExpiryDate("12/24");

        eWalletRequest = new PaymentMethodRequest();
        eWalletRequest.setPaymentType("e-wallet");
        eWalletRequest.setPhoneNumber("081234567890");

        cardDetails = mock(PaymentDetails.class);
        eWalletDetails = mock(PaymentDetails.class);
    }

    @Test
    void testCreatePaymentMethodCard() {
        when(cardPaymentStrategy.processPayment(any(PaymentMethodRequest.class))).thenReturn(cardDetails);

        PaymentMethod savedPaymentMethod = new PaymentMethod();
        savedPaymentMethod.setPaymentDetails(cardDetails);
        when(paymentMethodRepository.save(any(PaymentMethod.class))).thenReturn(savedPaymentMethod);

        PaymentMethod paymentMethod = paymentMethodService.createPaymentMethod(cardRequest);

        assertNotNull(paymentMethod);
        assertEquals(cardDetails, paymentMethod.getPaymentDetails());

        ArgumentCaptor<PaymentMethod> paymentMethodCaptor = ArgumentCaptor.forClass(PaymentMethod.class);
        verify(paymentMethodRepository, times(1)).save(paymentMethodCaptor.capture());

        PaymentMethod capturedPaymentMethod = paymentMethodCaptor.getValue();
        assertEquals(cardDetails, capturedPaymentMethod.getPaymentDetails());
        verify(cardPaymentStrategy, times(1)).processPayment(cardRequest);
    }

    @Test
    void testCreatePaymentMethodEWallet() {
        when(eWalletPaymentStrategy.processPayment(any(PaymentMethodRequest.class))).thenReturn(eWalletDetails);

        PaymentMethod savedPaymentMethod = new PaymentMethod();
        savedPaymentMethod.setPaymentDetails(eWalletDetails);
        when(paymentMethodRepository.save(any(PaymentMethod.class))).thenReturn(savedPaymentMethod);

        PaymentMethod paymentMethod = paymentMethodService.createPaymentMethod(eWalletRequest);

        assertNotNull(paymentMethod);
        assertEquals(eWalletDetails, paymentMethod.getPaymentDetails());

        ArgumentCaptor<PaymentMethod> paymentMethodCaptor = ArgumentCaptor.forClass(PaymentMethod.class);
        verify(paymentMethodRepository, times(1)).save(paymentMethodCaptor.capture());

        PaymentMethod capturedPaymentMethod = paymentMethodCaptor.getValue();
        assertEquals(eWalletDetails, capturedPaymentMethod.getPaymentDetails());
        verify(eWalletPaymentStrategy, times(1)).processPayment(eWalletRequest);
    }

    @Test
    void testUnsupportedPaymentType() {
        PaymentMethodRequest request = new PaymentMethodRequest();
        request.setPaymentType("unsupported");

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            paymentMethodService.createPaymentMethod(request);
        });

        assertEquals("Unsupported payment type: unsupported", exception.getMessage());
    }

    @Test
    void testGetAllPaymentMethods() {
        List<PaymentMethod> paymentMethodList = new ArrayList<>();
        PaymentMethod paymentMethod1 = new PaymentMethod();
        PaymentMethod paymentMethod2 = new PaymentMethod();
        paymentMethodList.add(paymentMethod1);
        paymentMethodList.add(paymentMethod2);

        when(paymentMethodRepository.findAll()).thenReturn(paymentMethodList);

        List<PaymentMethod> result = paymentMethodService.getAllPaymentMethods();

        assertEquals(2, result.size());
        assertEquals(paymentMethodList, result);
        verify(paymentMethodRepository, times(1)).findAll();
    }

    @Test
    void testDeletePaymentMethod() {
        String paymentId = UUID.randomUUID().toString();

        doNothing().when(paymentMethodRepository).deleteById(paymentId);

        paymentMethodService.deletePaymentMethod(paymentId);

        verify(paymentMethodRepository, times(1)).deleteById(paymentId);
    }

    @Test
    void testGetPaymentMethodById() {
        String paymentMethodId = UUID.randomUUID().toString();
        PaymentMethod paymentMethod = new PaymentMethod();
        when(paymentMethodRepository.findById(paymentMethodId)).thenReturn(Optional.of(paymentMethod));

        PaymentMethod result = paymentMethodService.getPaymentMethodById(paymentMethodId);

        assertNotNull(result);
        assertEquals(paymentMethod, result);
        verify(paymentMethodRepository, times(1)).findById(paymentMethodId);
    }

    @Test
    void testGetPaymentMethodByIdNotFound() {
        String paymentMethodId = UUID.randomUUID().toString();
        when(paymentMethodRepository.findById(paymentMethodId)).thenReturn(Optional.empty());

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            paymentMethodService.getPaymentMethodById(paymentMethodId);
        });

        assertEquals("Payment method not found with ID: " + paymentMethodId, exception.getMessage());
        verify(paymentMethodRepository, times(1)).findById(paymentMethodId);
    }

    @Test
    void testGetPaymentMethodsByUserId() {
        int userId = 1;
        List<PaymentMethod> paymentMethodList = new ArrayList<>();
        paymentMethodList.add(new PaymentMethod());
        when(paymentMethodRepository.findByUserId(userId)).thenReturn(Optional.of(paymentMethodList));

        List<PaymentMethod> result = paymentMethodService.getPaymentMethodByUserId(userId);

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(paymentMethodRepository, times(1)).findByUserId(userId);
    }

    @Test
    void testGetPaymentMethodsByUserIdNotFound() {
        int userId = 1;
        when(paymentMethodRepository.findByUserId(userId)).thenReturn(Optional.empty());

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            paymentMethodService.getPaymentMethodByUserId(userId);
        });

        assertEquals("Payment method not found with user ID: " + userId, exception.getMessage());
        verify(paymentMethodRepository, times(1)).findByUserId(userId);
    }
}



