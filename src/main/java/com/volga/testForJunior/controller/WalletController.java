package com.volga.testForJunior.controller;

import com.volga.testForJunior.domain.Wallet;
import com.volga.testForJunior.dto.WalletOperationRequest;
import com.volga.testForJunior.service.WalletService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/wallets")
@AllArgsConstructor
@Tag(name = "Банковский кошелёк", description = "Контроллер для получения баланса кошелька, а так же " +
        "возможности положить или снять средства")
public class WalletController {
    private final WalletService walletService;

    @Operation(summary = "Баланс кошелька", description = "Для получения баланса кошелька передайте в запросе " +
            "UUID кошелька")
    @GetMapping("/{walletId}")
    public ResponseEntity<BigDecimal> getWalletBalance(@PathVariable UUID walletId) {
        Optional<Wallet> wallet = walletService.getWallet(walletId);
        return wallet.map(value -> ResponseEntity.ok(value.getBalance()))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Внесение/снятие средств", description = "Для внесения или снятия средств передайте " +
            "в теле запроса JSON объект состоящий из UUID \"walletId\", \"DEPOSIT или WITHDRAW\", сумму \"amount\"")
    @PostMapping
    public ResponseEntity<Void> updateWallet(@RequestBody WalletOperationRequest request) {
        try {
            walletService.updateBalance(request.getWalletId(), request.getAmount(), request.getOperationType());
            return ResponseEntity.ok().build();
            } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}


