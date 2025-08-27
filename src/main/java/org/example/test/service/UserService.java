package org.example.test.service;

import org.example.test.dto.UserRequestDto;
import org.example.test.dto.UserResponseDto;

import java.util.List;

public interface UserService {

    UserResponseDto addUser(UserRequestDto userRequestDto);
    List<UserResponseDto> findAll();
    UserResponseDto findById(Long id);
    UserResponseDto updateUser(Long id,UserRequestDto userRequestDto);
    void delete(Long id);
}
