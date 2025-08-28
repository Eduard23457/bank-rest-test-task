package org.example.test.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.example.test.dto.UserRequestDto;
import org.example.test.dto.UserResponseDto;
import org.example.test.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Tag(name = "Пользователи", description = "Операции по управлению пользователями")
public class Controller {

    private final UserService userService;

    @Operation(summary = "Создать пользователя", description = "Администратор создаёт нового пользователя")
    @PostMapping("/add")
    public ResponseEntity<UserResponseDto> addUser(@RequestBody UserRequestDto userRequestDto) {
        UserResponseDto userResponse = userService.addUser(userRequestDto);
        return ResponseEntity.ok(userResponse);
    }

    @Operation(summary = "Посмотреть всех пользователей", description = "Администратор видит список всех пользователей")
    @GetMapping("/findAll")
    public ResponseEntity<List<UserResponseDto>> findAll() {
        return ResponseEntity.ok(userService.findAll());
    }

    @Operation(summary = "Найти пользователя по ID", description = "Получить информацию о конкретном пользователе")
    @GetMapping("/findById/{id}")
    public ResponseEntity<UserResponseDto> findById(@PathVariable Long id) {
        UserResponseDto user = userService.findById(id);
        return ResponseEntity.ok(user);
    }

    @Operation(summary = "Обновить пользователя", description = "Администратор обновляет данные пользователя")
    @PutMapping("/update/{id}")
    public ResponseEntity<UserResponseDto> updateUserById
            (@PathVariable Long id, @RequestBody UserRequestDto userRequestDto) {
        return ResponseEntity.ok(userService.updateUser(id, userRequestDto));
    }

    @Operation(summary = "Удалить пользователя", description = "Администратор удаляет пользователя из системы")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteById(Long id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

