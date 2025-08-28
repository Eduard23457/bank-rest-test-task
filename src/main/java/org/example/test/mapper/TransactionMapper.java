package org.example.test.mapper;

import org.example.test.dto.TransactionRequestDto;
import org.example.test.dto.TransactionResponseDto;

import org.example.test.entity.Transaction;
import org.mapstruct.Mapper;
import java.util.List;

@Mapper(componentModel = "spring")
public interface TransactionMapper {

    Transaction toEntity(TransactionRequestDto dto);

    TransactionResponseDto toDto(Transaction transaction);

    List<TransactionResponseDto> toDtoList(List<Transaction> transactions);
}