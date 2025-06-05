package org.example.userauthservice_asw_may2025.dtos;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ValidateTokenRequestDto {
    private Long userId;
    private String token;
}
