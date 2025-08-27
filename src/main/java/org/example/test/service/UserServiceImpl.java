package org.example.test.service;

import lombok.RequiredArgsConstructor;
import org.example.test.dto.UserRequestDto;
import org.example.test.dto.UserResponseDto;
import org.example.test.entity.User;
import org.example.test.mapper.UserMapper;
import org.example.test.repository.UserRepository;
import org.springframework.stereotype.Service;
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
}

