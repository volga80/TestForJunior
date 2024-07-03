package com.volga.testForJunior.service;

import com.volga.testForJunior.domain.OperationType;
import com.volga.testForJunior.domain.Wallet;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

public interface WalletService {

    Optional<Wallet> getWallet(UUID walletId);

    Wallet updateBalance(UUID walletId, BigDecimal amount, OperationType operationType);
}
