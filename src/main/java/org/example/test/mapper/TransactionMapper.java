package org.example.test.mapper;

import org.example.test.dto.TransactionRequestDto;
import org.example.test.dto.TransactionResponseDto;
import org.example.test.entity.Transaction;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TransactionMapper {

    @Mapping(source = "fromCardId", target = "fromCard.id")
    @Mapping(source = "toCardId", target = "toCard.id")
    Transaction toEntity(TransactionRequestDto dto);

    @Mapping(source = "fromCard.id", target = "fromCardNumber")
    @Mapping(source = "toCard.id", target = "toCardNumber")
    TransactionResponseDto toDto(Transaction transaction);

    List<TransactionResponseDto> toDtoList(List<Transaction> transactions);
}