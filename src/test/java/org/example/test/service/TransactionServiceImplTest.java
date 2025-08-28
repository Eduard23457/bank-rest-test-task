package org.example.test.service;

import org.example.test.dto.TransactionRequestDto;
import org.example.test.dto.TransactionResponseDto;
import org.example.test.entity.Card;
import org.example.test.entity.Transaction;
import org.example.test.mapper.TransactionMapper;
import org.example.test.repository.CardRepository;
import org.example.test.repository.TransactionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TransactionServiceTest {

    @Mock
    private TransactionRepository transactionRepository;
    @Mock
    private CardRepository cardRepository;
    @Mock
    private TransactionMapper transactionMapper;

    @InjectMocks
    private TransactionServiceImpl transactionService;

    private Card fromCard;
    private Card toCard;
    private Transaction transaction;
    private TransactionRequestDto requestDto;
    private TransactionResponseDto responseDto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        fromCard = new Card();
        fromCard.setId(1L);
        fromCard.setBalance(BigDecimal.valueOf(100));

        toCard = new Card();
        toCard.setId(2L);
        toCard.setBalance(BigDecimal.valueOf(50));

        transaction = new Transaction();
        transaction.setId(1L);
        transaction.setFromCard(fromCard);
        transaction.setToCard(toCard);
        transaction.setAmount(BigDecimal.valueOf(30));
        transaction.setCreatedAt(LocalDateTime.now());

        requestDto = new TransactionRequestDto(1L, 2L, BigDecimal.valueOf(30));
        responseDto = new TransactionResponseDto(
                1L,
                fromCard.getNumber(),
                toCard.getNumber(),
                BigDecimal.valueOf(500),
                LocalDateTime.now());
    }

    @Test
    void testCreateTransaction_Success() {
        when(cardRepository.findById(1L)).thenReturn(Optional.of(fromCard));
        when(cardRepository.findById(2L)).thenReturn(Optional.of(toCard));
        when(transactionRepository.save(any(Transaction.class))).thenReturn(transaction);
        when(transactionMapper.toDto(transaction)).thenReturn(responseDto);

        TransactionResponseDto result = transactionService.createTransaction(requestDto);
        assertNotNull(result);
        assertEquals(responseDto.getId(), result.getId());
        assertEquals(BigDecimal.valueOf(70), fromCard.getBalance());
        assertEquals(BigDecimal.valueOf(80), toCard.getBalance());
    }

    @Test
    void testCreateTransaction_InsufficientBalance() {
        fromCard.setBalance(BigDecimal.valueOf(10));
        when(cardRepository.findById(1L)).thenReturn(Optional.of(fromCard));
        when(cardRepository.findById(2L)).thenReturn(Optional.of(toCard));

        RuntimeException exception = assertThrows(RuntimeException.class, () -> transactionService.createTransaction(requestDto));
        assertEquals("Insufficient balance", exception.getMessage());
    }

    @Test
    void testFindAll() {
        when(transactionRepository.findAll()).thenReturn(List.of(transaction));
        when(transactionMapper.toDtoList(List.of(transaction))).thenReturn(List.of(responseDto));

        List<TransactionResponseDto> result = transactionService.findAll();
        assertEquals(1, result.size());
    }

    @Test
    void testFindById_Success() {
        when(transactionRepository.findById(1L)).thenReturn(Optional.of(transaction));
        when(transactionMapper.toDto(transaction)).thenReturn(responseDto);

        TransactionResponseDto result = transactionService.findById(1L);
        assertNotNull(result);
        assertEquals(transaction.getId(), result.getId());
    }

    @Test
    void testFindById_NotFound() {
        when(transactionRepository.findById(1L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> transactionService.findById(1L));
        assertEquals("Transaction not found", exception.getMessage());
    }
}