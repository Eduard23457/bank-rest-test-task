package org.example.test.service;

import org.example.test.entity.Role;
import org.example.test.dto.UserRequestDto;
import org.example.test.dto.UserResponseDto;
import org.example.test.entity.User;
import org.example.test.mapper.UserMapper;
import org.example.test.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserServiceImpl userService;

    private User user;
    private UserRequestDto requestDto;
    private UserResponseDto responseDto;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setId(1L);
        user.setUserName("testUser");

        requestDto = new UserRequestDto();
        requestDto.setUserName("testUser");
        requestDto.setPassword("pass");
        requestDto.setRole(Role.User);

        responseDto = new UserResponseDto();
        responseDto.setId(1L);
        responseDto.setUserName("testUser");
        responseDto.setRole(Role.User);
    }

    @Test
    void addUserSave() {
        when(userMapper.toEntity(requestDto)).thenReturn(user);
        when(userRepository.save(user)).thenReturn(user);
        when(userMapper.toDto(user)).thenReturn(responseDto);

        UserResponseDto result = userService.addUser(requestDto);

        assertEquals(responseDto.getId(), result.getId());
        assertEquals(responseDto.getUserName(), result.getUserName());
    }

    @Test
    void findAll() {
        when(userRepository.findAll()).thenReturn(List.of(user));
        when(userMapper.allUser(List.of(user))).thenReturn(List.of(responseDto));

        List<UserResponseDto> result = userService.findAll();

        assertEquals(1, result.size());
        assertEquals("testUser", result.get(0).getUserName());
    }

    @Test
    void findByIdUser() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(userMapper.toDto(user)).thenReturn(responseDto);

        UserResponseDto result = userService.findById(1L);

        assertEquals("testUser", result.getUserName());
    }

    @Test
    void findByIdNotFoundUser() {
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> userService.findById(1L));
    }

    @Test
    void updateUser() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        doNothing().when(userMapper).updateUserFromDto(requestDto, user);
        when(userRepository.save(user)).thenReturn(user);
        when(userMapper.toDto(user)).thenReturn(responseDto);

        UserResponseDto result = userService.updateUser(1L, requestDto);

        assertEquals("testUser", result.getUserName());
    }

    @Test
    void delete() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        userService.delete(1L);

        verify(userRepository, times(1)).delete(user);
    }
}