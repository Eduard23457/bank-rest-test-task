package org.example.test.controller;

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
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping("/createTransactional")
    public ResponseEntity<TransactionResponseDto> createTransaction(@RequestBody TransactionRequestDto dto) {
        return ResponseEntity.ok(transactionService.createTransaction(dto));
    }

    @GetMapping("/allTransaction")
    public ResponseEntity<List<TransactionResponseDto>> findAll() {
        return ResponseEntity.ok(transactionService.findAll());
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<TransactionResponseDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(transactionService.findById(id));
    }
}