package com.example.secondtreasurebe.service;

import com.example.secondtreasurebe.model.TopUpStatus;
import com.example.secondtreasurebe.model.TopUpTransaction;
import com.example.secondtreasurebe.repository.TopUpTransactionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class TopUpServiceImplTest {

    @Mock
    private TopUpTransactionRepository topUpTransactionRepository;

    @InjectMocks
    private TopUpServiceImpl topUpService;

    private TopUpTransaction topUpTransaction;

    @BeforeEach
    void setUp() {
        topUpTransaction = new TopUpTransaction();
        topUpTransaction.setId(UUID.randomUUID());
        topUpTransaction.setUserId(1);
        topUpTransaction.setAmount(BigDecimal.valueOf(1000));
        topUpTransaction.setPaymentMethodId(UUID.randomUUID());
        topUpTransaction.setStatus(TopUpStatus.PENDING);
    }

    @Test
    void testCreateTopUp() {
        when(topUpTransactionRepository.save(any(TopUpTransaction.class))).thenReturn(topUpTransaction);

        TopUpTransaction createdTransaction = topUpService.createTopUp(topUpTransaction.getUserId(), topUpTransaction.getAmount(), topUpTransaction.getPaymentMethodId());

        verify(topUpTransactionRepository, times(1)).save(any(TopUpTransaction.class));
        assertNotNull(createdTransaction);
        assertEquals(topUpTransaction, createdTransaction);
    }

    @Test
    void testGetAllTopUps() {
        when(topUpTransactionRepository.findAll()).thenReturn(Arrays.asList(topUpTransaction));

        List<TopUpTransaction> transactionList = topUpService.getAllTopUps();

        verify(topUpTransactionRepository, times(1)).findAll();
        assertFalse(transactionList.isEmpty());
        assertEquals(1, transactionList.size());
        assertEquals(topUpTransaction, transactionList.get(0));
    }

    @Test
    void testCancelTopUp() {
        when(topUpTransactionRepository.findById(topUpTransaction.getId())).thenReturn(Optional.of(topUpTransaction));

        topUpService.cancelTopUp(topUpTransaction.getId());

        verify(topUpTransactionRepository, times(1)).findById(topUpTransaction.getId());
        verify(topUpTransactionRepository, times(1)).save(topUpTransaction);
        assertEquals(TopUpStatus.CANCELED, topUpTransaction.getStatus());
    }

    @Test
    void testCancelTopUpInvalidState() {
        topUpTransaction.setStatus(TopUpStatus.APPROVED);
        when(topUpTransactionRepository.findById(topUpTransaction.getId())).thenReturn(Optional.of(topUpTransaction));

        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> {
            topUpService.cancelTopUp(topUpTransaction.getId());
        });

        assertEquals("Top-up is not in a state that can be cancelled.", exception.getMessage());
        verify(topUpTransactionRepository, times(1)).findById(topUpTransaction.getId());
        verify(topUpTransactionRepository, never()).save(topUpTransaction);
    }

    @Test
    void testCancelTopUpInvalidId() {
        UUID invalidId = UUID.randomUUID();
        when(topUpTransactionRepository.findById(invalidId)).thenReturn(Optional.empty());

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            topUpService.cancelTopUp(invalidId);
        });

        assertEquals("Invalid top-up ID: " + invalidId, exception.getMessage());
        verify(topUpTransactionRepository, times(1)).findById(invalidId);
        verify(topUpTransactionRepository, never()).save(any(TopUpTransaction.class));
    }
}
