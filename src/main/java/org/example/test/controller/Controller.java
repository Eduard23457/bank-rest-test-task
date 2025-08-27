package org.example.test.controller;

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
public class Controller {

    private final UserService userService;

    @PostMapping("/add")
    public ResponseEntity<UserResponseDto> addUser(@RequestBody UserRequestDto userRequestDto) {
        UserResponseDto userResponse = userService.addUser(userRequestDto);
        return ResponseEntity.ok(userResponse);
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<UserResponseDto>> findAll() {
        return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<UserResponseDto> findById(@PathVariable Long id) {
        UserResponseDto user = userService.findById(id);
        return ResponseEntity.ok(user);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<UserResponseDto> updateUserById
            (@PathVariable Long id, @RequestBody UserRequestDto userRequestDto) {
        return ResponseEntity.ok(userService.updateUser(id, userRequestDto));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void>deleteById(Long id){
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

