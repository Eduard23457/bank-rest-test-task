package org.example.test.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.test.entity.CardStatus;
import org.example.test.entity.User;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CardRequestDto {

    private Long userId;
    private LocalDate date;
    private CardStatus status;

}
