package com.volga.testForJunior.service;

import com.volga.testForJunior.dao.TransactionRepository;
import com.volga.testForJunior.dao.WalletRepository;
import com.volga.testForJunior.domain.OperationType;
import com.volga.testForJunior.domain.Transaction;
import com.volga.testForJunior.domain.Wallet;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class WalletServiceImpl implements WalletService {

    private final WalletRepository walletRepository;

    private final TransactionRepository transactionRepository;

    @Override
    public Optional<Wallet> getWallet(UUID walletId) {
        return walletRepository.findById(walletId);
    }

    @Override
    @Transactional
    public Wallet updateBalance(UUID walletId, BigDecimal amount, OperationType operationType) {
        Wallet wallet = walletRepository.findByIdForUpdate(walletId)
                .orElseThrow(() -> new RuntimeException("Кошелька не существует, проверьте UUID"));
        if (operationType.equals(OperationType.DEPOSIT)) {
            wallet.setBalance(wallet.getBalance().add(amount));
        } else if (operationType.equals(OperationType.WITHDRAW)) {
            if (wallet.getBalance().compareTo(amount) < 0) {
                throw new RuntimeException("Недостаточно средств");
            }
            wallet.setBalance(wallet.getBalance().subtract(amount));
        }
        Transaction transaction = Transaction.builder()
                .wallet(wallet)
                .id(UUID.randomUUID())
                .amount(amount)
                .operationType(operationType)
                .timestamp(LocalDateTime.now())
                .build();
        transactionRepository.save(transaction);
        return walletRepository.save(wallet);
    }
}
