package org.example.test.service;

import org.example.test.dto.CardRequestDto;
import org.example.test.dto.CardResponseDto;
import org.example.test.entity.Card;
import org.example.test.entity.CardStatus;
import org.example.test.entity.User;
import org.example.test.mapper.CardMapper;
import org.example.test.repository.CardRepository;
import org.example.test.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CardServiceTest {

    @Mock
    private CardRepository cardRepository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private CardMapper cardMapper;

    @InjectMocks
    private CardServiceImpl cardService;

    private User user;
    private Card card;
    private CardRequestDto requestDto;
    private CardResponseDto responseDto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        user = new User();
        user.setId(1L);

        card = new Card();
        card.setId(1L);
        card.setUser(user);
        card.setStatus(CardStatus.Active);
        card.setBalance(BigDecimal.TEN);
        card.setDate(LocalDate.now().plusYears(2));
        card.setNumber("1234 5678 9012 3456");

        requestDto = new CardRequestDto(1L, LocalDate.now(), CardStatus.Active);
        responseDto = new CardResponseDto(1L, 1L, "**** **** **** 3456", "testUser", LocalDate.now().plusYears(2), CardStatus.Active, BigDecimal.TEN);
    }

    @Test
    void testAddCard_Success() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(cardRepository.save(any(Card.class))).thenReturn(card);
        when(cardMapper.toDto(card)).thenReturn(responseDto);

        CardResponseDto result = cardService.addCard(requestDto);

        assertNotNull(result);
        assertEquals(responseDto.getId(), result.getId());
        verify(cardRepository, times(1)).save(any(Card.class));
    }

    @Test
    void testAddCard_UserNotFound() {
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> cardService.addCard(requestDto));
        assertEquals("нет такого user", exception.getMessage());
    }

    @Test
    void testFindAll() {
        when(cardRepository.findAll()).thenReturn(List.of(card));
        when(cardMapper.findAllCard(List.of(card))).thenReturn(List.of(responseDto));

        List<CardResponseDto> result = cardService.findAll();
        assertEquals(1, result.size());
        verify(cardRepository, times(1)).findAll();
    }

    @Test
    void testFindByIdCard_Success() {
        when(cardRepository.findById(1L)).thenReturn(Optional.of(card));
        when(cardMapper.toDto(card)).thenReturn(responseDto);

        CardResponseDto result = cardService.findByIdCard(1L);
        assertNotNull(result);
        assertEquals(card.getId(), result.getId());
    }

    @Test
    void testFindByIdCard_NotFound() {
        when(cardRepository.findById(1L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> cardService.findByIdCard(1L));
        assertEquals("нету такого id", exception.getMessage());
    }

    @Test
    void testDeleteById_Success() {
        when(cardRepository.findById(1L)).thenReturn(Optional.of(card));
        doNothing().when(cardRepository).delete(card);

        cardService.deleteById(1L);
        verify(cardRepository, times(1)).delete(card);
    }

    @Test
    void testDeleteById_NotFound() {
        when(cardRepository.findById(1L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> cardService.deleteById(1L));
        assertEquals("нету такого id", exception.getMessage());
    }
}