package org.example.test.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.test.entity.CardStatus;
import org.example.test.entity.User;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CardResponseDto {

    private Long id;
    private Long userId;
    private String number;
    private LocalDate date;
    private CardStatus status;
    private BigDecimal balance = BigDecimal.ZERO;
}
