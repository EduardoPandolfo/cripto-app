package com.eduardokp.criptoapp.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class LoginDTO {
    @NotBlank(message = "Login - Username is obrigatory")
    private String username;
    @NotBlank(message = "Login - Password is obrigatory")
    private String password;
}
