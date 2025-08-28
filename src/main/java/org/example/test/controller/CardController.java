package org.example.test.controller;

import lombok.RequiredArgsConstructor;
import org.example.test.dto.CardRequestDto;
import org.example.test.dto.CardResponseDto;
import org.example.test.service.CardService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class CardController {

    private final CardService cardService;

    @PostMapping("/add")
    public ResponseEntity<CardResponseDto> addCardInBd(@RequestBody CardRequestDto cardRequestDto) {
        CardResponseDto cardResponseDto = cardService.addCard(cardRequestDto);
        return ResponseEntity.ok(cardResponseDto);
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<CardResponseDto>> allFind() {
        return ResponseEntity.ok(cardService.findAll());
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<CardResponseDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(cardService.findByIdCard(id));
    }
@PutMapping("/update/{id}")
    public ResponseEntity<CardResponseDto> updateCardById
            (@PathVariable Long id, @RequestBody CardRequestDto cardRequestDto) {
        return ResponseEntity.ok(cardService.updateCard(id, cardRequestDto));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void>deleteCardFromDto(@PathVariable Long id){
        cardService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
