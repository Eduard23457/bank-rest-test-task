package org.example.test.service;

import lombok.RequiredArgsConstructor;
import org.example.test.dto.UserRequestDto;
import org.example.test.dto.UserResponseDto;
import org.example.test.entity.User;
import org.example.test.mapper.UserMapper;
import org.example.test.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper;
    private final UserRepository userRepository;


    @Override
    public UserResponseDto addUser(UserRequestDto userRequestDto) {
        User user = userMapper.toEntity(userRequestDto);
        User save = userRepository.save(user);
        return userMapper.toDto(save);
    }

    @Override
    public List<UserResponseDto> findAll() {
        List<User> all = userRepository.findAll();
        List<UserResponseDto> userResponseDtos = userMapper.allUser(all);
        return userResponseDtos;
    }

    @Override
    public UserResponseDto findById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Нету такого id"));
        UserResponseDto dto = userMapper.toDto(user);
        return dto;
    }

    @Override
    public UserResponseDto updateUser(Long id, UserRequestDto userRequestDto) {
        User user = userRepository.findById(id).orElseThrow(() ->
                new RuntimeException("нету такого id"));
        userMapper.updateUserFromDto(userRequestDto, user);
        User save = userRepository.save(user);
        return userMapper.toDto(save);
    }

    @Override
    public void delete(Long id) {
        User user = userRepository.findById(id).orElseThrow(() ->
                new RuntimeException("нету такого id"));
        userRepository.delete(user);
    }

}

