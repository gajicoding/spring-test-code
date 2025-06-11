package com.sparta.test_code.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserCreationDto {

    @NotNull
    private String name;

    @NotNull
    private String email;

    @NotNull
    private String password;
}
