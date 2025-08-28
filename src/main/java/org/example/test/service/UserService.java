package org.example.test.service;

import org.example.test.dto.UserRequestDto;
import org.example.test.dto.UserResponseDto;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

public interface UserService {
    @PreAuthorize("hasRole('ADMIN')")
    UserResponseDto addUser(UserRequestDto userRequestDto);

    @PreAuthorize("hasRole('ADMIN')")
    List<UserResponseDto> findAll();

    @PreAuthorize("hasRole('ADMIN')")
    UserResponseDto findById(Long id);

    @PreAuthorize("hasRole('ADMIN')")
    UserResponseDto updateUser(Long id, UserRequestDto userRequestDto);

    @PreAuthorize("hasRole('ADMIN')")
    void delete(Long id);
}
