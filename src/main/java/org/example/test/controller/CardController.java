package org.example.test.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.example.test.dto.CardRequestDto;
import org.example.test.dto.CardResponseDto;
import org.example.test.service.CardService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
@Tag(name = "Банковские карты", description = "Операции с картами")
public class CardController {

    private final CardService cardService;

    @Operation(summary = "Добавить карту",
            description = "Администратор создаёт новую банковскую карту для пользователя. Номер карты генерируется автоматически.")
    @PostMapping("/add")
    public ResponseEntity<CardResponseDto> addCardInBd(@RequestBody CardRequestDto cardRequestDto) {
        CardResponseDto cardResponseDto = cardService.addCard(cardRequestDto);
        return ResponseEntity.ok(cardResponseDto);
    }

    @Operation(summary = "Просмотреть все карты", description = "Администратор видит список всех карт пользователей")
    @GetMapping("/findAll")
    public ResponseEntity<List<CardResponseDto>> allFind() {
        return ResponseEntity.ok(cardService.findAll());
    }

    @Operation(summary = "Найти карту по ID", description = "Получить информацию о конкретной карте по её уникальному ID")
    @GetMapping("/findById/{id}")
    public ResponseEntity<CardResponseDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(cardService.findByIdCard(id));
    }

    @Operation(summary = "Обновить данные карты", description = "Администратор обновляет статус карты или дату окончания действия")
    @PutMapping("/update/{id}")
    public ResponseEntity<CardResponseDto> updateCardById
            (@PathVariable Long id, @RequestBody CardRequestDto cardRequestDto) {
        return ResponseEntity.ok(cardService.updateCard(id, cardRequestDto));
    }

    @Operation(summary = "Удалить карту", description = "Администратор удаляет карту из системы по её ID")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteCardFromDto(@PathVariable Long id) {
        cardService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
