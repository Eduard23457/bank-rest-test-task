package org.example.test.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionRequestDto {

    @Schema(description = "ID карты отправителя", example = "1")
    private Long fromCardId;
    @Schema(description = "ID карты получателя", example = "1")
    private Long toCardId;
    @Schema(description = "Сумма перевода", example = "500")
    private BigDecimal amount;
}