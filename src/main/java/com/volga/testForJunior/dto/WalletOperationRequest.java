package com.volga.testForJunior.dto;

import com.volga.testForJunior.domain.OperationType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Schema(description = "Запрос на внесение или снятие средств")
public class WalletOperationRequest {

    @Schema(description = "UUID номер кошелька")
    private UUID walletId;

    @Schema(description = "Тип операции DEPOSIT/WITHDRAW")
    private OperationType operationType;

    @Schema(description = "Сумма внесенных или снимаемых средств")
    private BigDecimal amount;
}
