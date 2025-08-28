package org.example.test.service;

import org.example.test.dto.CardRequestDto;
import org.example.test.dto.CardResponseDto;

import java.util.List;

public interface CardService {

    CardResponseDto addCard(CardRequestDto cardRequestDto);

    List<CardResponseDto> findAll();

    CardResponseDto findByIdCard(Long id);

    CardResponseDto updateCard(Long id, CardRequestDto cardRequestDto);

    void deleteById(Long id);

}
