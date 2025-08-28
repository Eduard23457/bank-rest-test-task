package org.example.test.service;

import org.example.test.dto.CardRequestDto;
import org.example.test.dto.CardResponseDto;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

public interface CardService {
    @PreAuthorize("hasRole('ADMIN')")
    CardResponseDto addCard(CardRequestDto cardRequestDto);

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    List<CardResponseDto> findAll();

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    CardResponseDto findByIdCard(Long id);

    @PreAuthorize("hasRole('ADMIN')")
    CardResponseDto updateCard(Long id, CardRequestDto cardRequestDto);

    @PreAuthorize("hasRole('ADMIN')")
    void deleteById(Long id);

}
