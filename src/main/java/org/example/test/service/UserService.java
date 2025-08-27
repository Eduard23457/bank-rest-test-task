package org.example.test.service;

import org.example.test.dto.UserRequestDto;
import org.example.test.dto.UserResponseDto;

public interface UserService {

    UserResponseDto addUser(UserRequestDto userRequestDto);
}
