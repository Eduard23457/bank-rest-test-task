package org.example.test.service;

import org.example.test.dto.TransactionRequestDto;
import org.example.test.dto.TransactionResponseDto;
import java.util.List;

public interface TransactionService {
    TransactionResponseDto createTransaction(TransactionRequestDto dto);
    List<TransactionResponseDto> findAll();
    TransactionResponseDto findById(Long id);
}