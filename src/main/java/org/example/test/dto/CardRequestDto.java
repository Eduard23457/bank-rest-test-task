package org.example.test.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.test.entity.CardStatus;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CardRequestDto {

    @Schema(description = "ID пользователя, которому принадлежит карта", example = "1")
    private Long userId;

    @Schema(description = "Дата окончания действия карты", example = "2027-08-28")
    private LocalDate date;

    @Schema(description = "Статус карты", example = "ACTIVE")
    private CardStatus status;



}
