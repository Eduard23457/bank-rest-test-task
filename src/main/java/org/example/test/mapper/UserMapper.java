package org.example.test.mapper;

import org.example.test.dto.UserRequestDto;
import org.example.test.dto.UserResponseDto;
import org.example.test.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;


import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toEntity(UserRequestDto userRequestDto);

    UserResponseDto toDto(User user);

    List<UserResponseDto> allUser(List<User> users);

    @Mapping(target = "id",ignore = true)
    void updateUserFromDto(UserRequestDto userRequestDto, @MappingTarget User user);
}