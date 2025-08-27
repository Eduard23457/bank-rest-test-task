package org.example.test.controller;

import lombok.RequiredArgsConstructor;
import org.example.test.dto.UserRequestDto;
import org.example.test.dto.UserResponseDto;
import org.example.test.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class Controller {

    private final UserService userService;

    @PostMapping("/add")
    public ResponseEntity<UserResponseDto>addUser(UserRequestDto userRequestDto){
        UserResponseDto userResponse = userService.addUser(userRequestDto);
        return ResponseEntity.ok(userResponse);
    }
}
