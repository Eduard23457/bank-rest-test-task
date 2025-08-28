package org.example.test.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.test.entity.CardStatus;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CardResponseDto {

    @Schema(description = "Уникальный ID карты", example = "101")
    private Long id;

    @Schema(description = "ID владельца карты", example = "1")
    private Long userId;

    @Schema(description = "Номер карты, замаскированный для безопасности (**** **** **** 1234)", example = "**** **** **** 1234")
    private String number;

    private String username;

    @Schema(description = "Дата окончания действия карты", example = "2027-08-28")
    private LocalDate date;

    @Schema(description = "Статус карты", example = "ACTIVE")
    private CardStatus status;

    @Schema(description = "Баланс карты", example = "1500")
    private BigDecimal balance = BigDecimal.ZERO;


}
