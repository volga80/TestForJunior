package com.volga.testForJunior.controller;

import com.volga.testForJunior.domain.OperationType;
import com.volga.testForJunior.domain.Wallet;
import com.volga.testForJunior.dto.WalletOperationRequest;
import com.volga.testForJunior.service.WalletService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class WalletControllerTest {

    @InjectMocks
    WalletController walletController;

    @Mock
    WalletService walletService;

    MockMvc mockMvc;

    UUID id = UUID.randomUUID();

    private Wallet wallet;

    private WalletOperationRequest request;

    @BeforeEach
    void setUp() {
        request = new WalletOperationRequest();
        request.setWalletId(id);
        request.setAmount(BigDecimal.valueOf(1000));
        request.setOperationType(OperationType.DEPOSIT);
        wallet = new Wallet();
        wallet.setBalance(BigDecimal.valueOf(1000.00));
        wallet.setId(id);
        mockMvc = MockMvcBuilders.standaloneSetup(walletController).build();
    }

    @Test
    void walletBalance_whereUuidIsValid() throws Exception {
        when(walletService.getWallet(id)).thenReturn(Optional.ofNullable(wallet));

        mockMvc.perform(get("/api/v1/wallets/" + id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("1000.0"));
    }

    @Test
    void testGetWalletBalance_WalletNotFound() throws Exception {
        when(walletService.getWallet(id)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/v1/wallets/" + id))
                .andExpect(status().isNotFound());
    }

//    @Test
//    void testUpdateWallet_WhereRequestIsCorrect() throws Exception {
//        when(walletService.updateBalance(request.getWalletId(), request.getAmount(),
//                request.getOperationType())).thenReturn(wallet);
//
//        mockMvc.perform(post("/api/v1/wallets")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content("{\"walletId\":\"" + id + "\", \"operationType\":\"DEPOSIT\", \"amount\":\"1000.0\"}"))
//                .andExpect(status().isOk());
//    }
}