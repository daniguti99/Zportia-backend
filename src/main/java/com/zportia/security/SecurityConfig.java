package com.zportia.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

//Clase de configuración de seguridad para la aplicación
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // Configuración de seguridad (puedes personalizarla según tus necesidades)
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) {
        return configuration.getAuthenticationManager();
    }
}
