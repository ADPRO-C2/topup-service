package com.example.secondtreasurebe.controller;

import com.example.secondtreasurebe.model.TopUpTransaction;
import com.example.secondtreasurebe.service.TopUpService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@ExtendWith(MockitoExtension.class)
class TopUpControllerTest {

    @Mock
    private TopUpService topUpService;

    @InjectMocks
    private TopUpController topUpController;

    private MockMvc mockMvc;

    private TopUpTransaction topUpTransaction;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(topUpController).build();
        topUpTransaction = new TopUpTransaction();
        topUpTransaction.setId(UUID.randomUUID());
        topUpTransaction.setUserId(UUID.randomUUID());
        topUpTransaction.setAmount(BigDecimal.valueOf(1000));
        topUpTransaction.setPaymentMethodId(UUID.randomUUID());
        topUpTransaction.setStatus("pending");
    }

    @Test
    void testCreateTopUp() throws Exception {
        when(topUpService.createTopUp(any(UUID.class), any(BigDecimal.class), any(UUID.class)))
                .thenReturn(topUpTransaction);

        mockMvc.perform(post("/topups")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"userId\":\"" + topUpTransaction.getUserId() + "\",\"amount\":1000,\"paymentMethodId\":\"" + topUpTransaction.getPaymentMethodId() + "\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(topUpTransaction.getId().toString()))
                .andExpect(jsonPath("$.status").value("pending"))
                .andDo(print());

        verify(topUpService, times(1)).createTopUp(any(UUID.class), any(BigDecimal.class), any(UUID.class));
    }

    @Test
    void testGetAllTopUps() throws Exception {
        List<TopUpTransaction> transactions = Arrays.asList(topUpTransaction);
        when(topUpService.getAllTopUps()).thenReturn(transactions);

        mockMvc.perform(get("/topups")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(topUpTransaction.getId().toString()))
                .andDo(print());

        verify(topUpService, times(1)).getAllTopUps();
    }

    @Test
    void testCancelTopUp() throws Exception {
        doNothing().when(topUpService).cancelTopUp(any(UUID.class));

        mockMvc.perform(post("/topups/{topUpId}/cancel", topUpTransaction.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());

        verify(topUpService, times(1)).cancelTopUp(any(UUID.class));
    }
}
