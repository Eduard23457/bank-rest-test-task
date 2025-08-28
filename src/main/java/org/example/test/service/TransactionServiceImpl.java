package org.example.test.service;

import lombok.RequiredArgsConstructor;
import org.example.test.dto.TransactionRequestDto;
import org.example.test.dto.TransactionResponseDto;
import org.example.test.entity.Card;
import org.example.test.entity.Transaction;
import org.example.test.mapper.TransactionMapper;
import org.example.test.repository.CardRepository;
import org.example.test.repository.TransactionRepository;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final CardRepository cardRepository;
    private final TransactionMapper transactionMapper;

    @Override
    public TransactionResponseDto createTransaction(TransactionRequestDto dto) {
        Card fromCard = cardRepository.findById(dto.getFromCardId())
                .orElseThrow(() -> new RuntimeException("From card not found"));
        Card toCard = cardRepository.findById(dto.getToCardId())
                .orElseThrow(() -> new RuntimeException("To card not found"));

        if (fromCard.getBalance().compareTo(dto.getAmount()) < 0) {
            throw new RuntimeException("Insufficient balance");
        }

        fromCard.setBalance(fromCard.getBalance().subtract(dto.getAmount()));
        toCard.setBalance(toCard.getBalance().add(dto.getAmount()));

        Transaction transaction = new Transaction();


        transaction.setFromCard(fromCard);
        transaction.setToCard(toCard);
        transaction.setAmount(dto.getAmount());
        transaction.setCreatedAt(LocalDateTime.now());

        Transaction saved = transactionRepository.save(transaction);
        return transactionMapper.toDto(saved);
    }

    @Override
    public List<TransactionResponseDto> findAll() {
        List<Transaction> transactions = transactionRepository.findAll();
        return transactionMapper.toDtoList(transactions);
    }

    @Override
    public TransactionResponseDto findById(Long id) {
        Transaction transaction = transactionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Transaction not found"));
        return transactionMapper.toDto(transaction);
    }
}