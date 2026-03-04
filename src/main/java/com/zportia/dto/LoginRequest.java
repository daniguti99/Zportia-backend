package com.zportia.dto;

import lombok.Data;

// DTO para la solicitud de inicio de sesión

//Lo que el clinete envía al servidor para iniciar sesión, contiene el email y la contraseña del usuario
@Data
public class LoginRequest {
    private String email;
    private String password;
}
