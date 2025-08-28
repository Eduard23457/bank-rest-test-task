package org.example.test.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.example.test.dto.TransactionRequestDto;
import org.example.test.dto.TransactionResponseDto;
import org.example.test.service.TransactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
@RequiredArgsConstructor
@Tag(name = "Транзакции", description = "Операции по переводу денег между картами")
public class TransactionController {

    private final TransactionService transactionService;

    @Operation(summary = "Создать транзакцию", description = "Пользователь переводит деньги с одной своей карты на другую.")
    @PostMapping("/createTransactional")
    public ResponseEntity<TransactionResponseDto> createTransaction(@RequestBody TransactionRequestDto dto) {
        return ResponseEntity.ok(transactionService.createTransaction(dto));
    }

    @Operation(summary = "Посмотреть все транзакции", description = "Администратор видит список всех транзакций")
    @GetMapping("/allTransaction")
    public ResponseEntity<List<TransactionResponseDto>> findAll() {
        return ResponseEntity.ok(transactionService.findAll());
    }

    @Operation(summary = "Найти транзакцию по ID", description = "Получить информацию о конкретной транзакции по её уникальному ID")
    @GetMapping("/findById/{id}")
    public ResponseEntity<TransactionResponseDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(transactionService.findById(id));
    }
}