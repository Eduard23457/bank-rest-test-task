package org.example.test.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.test.entity.Role;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDto {

    @Schema(description = "Имя пользователя", example = "ivan123")
    private String userName;

    @Schema(description = "Пароль пользователя", example = "password123")
    private String password;

    @Schema(description = "Роль пользователя", example = "USER")
    private Role role;

    @Schema(description = "Активность пользователя", example = "true")
    private boolean enabled;
}




