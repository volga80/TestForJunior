package com.volga.testForJunior.service;

import com.volga.testForJunior.dao.TransactionRepository;
import com.volga.testForJunior.dao.WalletRepository;
import com.volga.testForJunior.domain.OperationType;
import com.volga.testForJunior.domain.Transaction;
import com.volga.testForJunior.domain.Wallet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.util.Supplier;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class WalletServiceImplTest {

    @InjectMocks
    private WalletServiceImpl walletService;

    @Mock
    private WalletRepository walletRepository;

    @Mock
    private TransactionRepository transactionRepository;


    private Wallet wallet;
    private UUID walletId;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        wallet = new Wallet();
        walletId = UUID.randomUUID();
        wallet.setId(walletId);
        wallet.setBalance(new BigDecimal("1000.00"));
    }


//    @Test
//    void testUpdateBalance_Deposit() {
//        when(walletRepository.findById(walletId)).thenReturn(Optional.of(wallet));
//        when(walletRepository.save(any(Wallet.class))).thenAnswer(invocation -> invocation.getArgument(0));
//        when(transactionRepository.save(any(Transaction.class))).thenAnswer(invocation -> invocation.getArgument(0));
//
//        Wallet updatedWallet = walletService.updateBalance(walletId, new BigDecimal("200.00"), OperationType.DEPOSIT);
//
//        assertEquals(new BigDecimal("1200.00"), updatedWallet.getBalance());
//
//        verify(walletRepository, times(1)).save(wallet);
//
//        ArgumentCaptor<Transaction> transactionCaptor = ArgumentCaptor.forClass(Transaction.class);
//        verify(transactionRepository, times(1)).save(transactionCaptor.capture());
//        Transaction savedTransaction = transactionCaptor.getValue();
//        assertEquals(wallet, savedTransaction.getWallet());
//        assertEquals(new BigDecimal("200.00"), savedTransaction.getAmount());
//        assertEquals(OperationType.DEPOSIT, savedTransaction.getOperationType());
//        assertNotNull(savedTransaction.getId());
//        assertNotNull(savedTransaction.getTimestamp());
//    }
//
//    @Test
//    void testUpdateBalance_Withdraw() {
//        when(walletRepository.findById(walletId).orElseThrow()).thenReturn(wallet);
//        when(walletRepository.save(any(Wallet.class))).thenReturn(wallet);
//        when(transactionRepository.save(any(Transaction.class))).thenReturn(new Transaction());
//
//        Wallet updatedWallet = walletService.updateBalance(walletId, new BigDecimal("200.00"), OperationType.WITHDRAW);
//
//        assertEquals(new BigDecimal("800.00"), updatedWallet.getBalance());
//        verify(walletRepository, times(1)).save(wallet);
//        verify(transactionRepository, times(1)).save(any(Transaction.class));
//    }
//
//    @Test
//    void testUpdateBalance_WithdrawInsufficientFunds() {
//        when(walletRepository.findById(walletId)).thenReturn(Optional.of(wallet));
//
//        Exception exception = assertThrows(RuntimeException.class, () -> {
//            walletService.updateBalance(walletId, new BigDecimal("2000.00"), OperationType.WITHDRAW);
//        });
//
//        assertEquals("Недостаточно средств", exception.getMessage());
//        verify(walletRepository, times(0)).save(wallet);
//        verify(transactionRepository, times(0)).save(any(Transaction.class));
//    }
//
//    @Test
//    void testUpdateBalance_WalletNotFound() {
//        when(walletRepository.findById(walletId)).thenReturn(Optional.empty());
//
//        Exception exception = assertThrows(RuntimeException.class, () -> {
//            walletService.updateBalance(walletId, new BigDecimal("200.00"), OperationType.DEPOSIT);
//        });
//
//        assertEquals("Кошелька не существует, проверьте UUID", exception.getMessage());
//        verify(walletRepository, times(0)).save(wallet);
//        verify(transactionRepository, times(0)).save(any(Transaction.class));
//    }
}