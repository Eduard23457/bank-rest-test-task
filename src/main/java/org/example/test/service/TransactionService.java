package org.example.test.service;

import org.example.test.dto.TransactionRequestDto;
import org.example.test.dto.TransactionResponseDto;
import org.springframework.security.access.prepost.PreAuthorize;
import java.util.List;

public interface TransactionService {
    @PreAuthorize("hasRole('USER')")
    TransactionResponseDto createTransaction(TransactionRequestDto dto);

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    List<TransactionResponseDto> findAll();

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    TransactionResponseDto findById(Long id);
}