package org.example.test.mapper;

import org.example.test.dto.CardRequestDto;
import org.example.test.dto.CardResponseDto;
import org.example.test.dto.UserRequestDto;
import org.example.test.entity.Card;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CardMapper {

    Card toEntity(CardRequestDto cardRequestDto);

    CardResponseDto toDto(Card card);

    List<CardResponseDto> findAllCard(List<Card> cards);
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "date", ignore = true)
    @Mapping(target = "balance", ignore = true)
    void updateCard(CardRequestDto cardRequestDto, @MappingTarget Card card);
}
