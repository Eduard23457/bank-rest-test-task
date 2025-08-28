package org.example.test.service;

import lombok.RequiredArgsConstructor;
import org.example.test.dto.CardRequestDto;
import org.example.test.dto.CardResponseDto;
import org.example.test.entity.Card;
import org.example.test.entity.User;
import org.example.test.mapper.CardMapper;
import org.example.test.mapper.UserMapper;
import org.example.test.repository.CardRepository;
import org.example.test.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CardServiceImpl implements CardService {

    private final CardMapper cardMapper;
    private final CardRepository cardRepository;
    private final UserRepository userRepository;


    @Override
    public CardResponseDto addCard(CardRequestDto cardRequestDto) {
        User user = userRepository.findById(cardRequestDto.getUserId())
                .orElseThrow(() -> new RuntimeException("нет такого user"));

        Card card = new Card();
        card.setUser(user);
        card.setStatus(cardRequestDto.getStatus());
        card.setDate(LocalDate.now().plusYears(2));
        card.setBalance(BigDecimal.ZERO);
        card.setNumber(generateCardNumber());

        Card save = cardRepository.save(card);
        CardResponseDto dto = cardMapper.toDto(save);
        dto.setNumber(maskCardNumber(dto.getNumber()));
        return dto;
    }

    private String generateCardNumber() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 16; i++) {
            sb.append((int) (Math.random() * 10));
            if ((i + 1) % 4 == 0 && i != 15) sb.append(" ");
        }
        return sb.toString();
    }

    private String maskCardNumber(String cardNumber) {
        if (cardNumber == null || cardNumber.length() < 4)
            return cardNumber;
        String last4 = cardNumber.substring(cardNumber.length() - 4);
        return "**** **** **** " + last4;
    }

    @Override
    public List<CardResponseDto> findAll() {
        List<Card> all = cardRepository.findAll();
        List<CardResponseDto> allCard = cardMapper.findAllCard(all);
        allCard.forEach(dto -> dto.setNumber(maskCardNumber(dto.getNumber())));
        return allCard;
    }

    @Override
    public CardResponseDto findByIdCard(Long id) {
        Card card = cardRepository.findById(id).orElseThrow(() ->
                new RuntimeException("нету такого id"));
        CardResponseDto dto = cardMapper.toDto(card);
        dto.setNumber(maskCardNumber(dto.getNumber()));
        return dto;


    }

    @Override
    public CardResponseDto updateCard(Long id, CardRequestDto cardRequestDto) {
        Card card = cardRepository.findById(id).orElseThrow(() ->
                new RuntimeException("нету такого id"));
        cardMapper.updateCard(cardRequestDto, card);
        Card save = cardRepository.save(card);
        CardResponseDto dto = cardMapper.toDto(save);
        dto.setNumber(maskCardNumber(dto.getNumber()));
        return dto;
    }

    @Override
    public void deleteById(Long id) {
        Card card = cardRepository.findById(id).orElseThrow(() ->
                new RuntimeException("нету такого id"));
        cardRepository.delete(card);

    }
}
