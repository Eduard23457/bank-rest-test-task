package org.example.test.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.test.entity.Role;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDto {

    private String userName;

    private String password;

    private Role role;

}




