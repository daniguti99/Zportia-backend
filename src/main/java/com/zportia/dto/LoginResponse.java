package com.zportia.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
// DTO para la respuesta del servidor de inicio de sesión

@Data
@AllArgsConstructor
public class LoginResponse {
    private String token;
}
