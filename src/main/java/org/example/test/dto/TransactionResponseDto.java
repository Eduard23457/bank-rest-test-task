package org.example.test.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionResponseDto {
    @Schema(description = "Уникальный ID транзакции", example = "101")
    private Long id;

    @Schema(description = "Номер карты отправителя, замаскированный", example = "**** **** **** 1234")
    private String fromCardNumber;

    @Schema(description = "Номер карты получателя, замаскированный", example = "**** **** **** 5678")
    private String toCardNumber;

    @Schema(description = "Сумма перевода", example = "500")
    private BigDecimal amount;

    @Schema(description = "Дата и время транзакции", example = "2025-08-28T15:30:00")
    private LocalDateTime createdAt;


}
