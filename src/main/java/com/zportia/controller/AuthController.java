package com.zportia.controller;

import com.zportia.dto.LoginRequest;
import com.zportia.dto.LoginResponse;
import com.zportia.security.JwtUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// Controlador para manejar la autenticación de usuarios (registro, inicio de sesión, etc.)
@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    public AuthController(AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }
    // Endpoint para el inicio de sesión, que recibe un LoginRequest con el email y la contraseña del usuario y devuelve un LoginResponse con el token JWT generado si la autenticación es exitosa
    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {
        // Intentamos autenticar al usuario utilizando el AuthenticationManager de Spring Security, pasando un UsernamePasswordAuthenticationToken
        // con el email y la contraseña. Si la autenticación es exitosa, se genera un token JWT utilizando el JwtUtil y se devuelve en la respuesta.
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

        // Si la autenticación es exitosa, se obtiene el usuario autenticado.
        UserDetails user = (UserDetails) authentication.getPrincipal();

        // El token JWT se genera utilizando el nombre de usuario y el rol del usuario autenticado, que creamos en el metodo generateToken del JwtUtil.
        String token = jwtUtil.generateToken(user.getUsername(), user.getAuthorities().iterator().next().getAuthority());

        // Finalmente, se devuelve un LoginResponse que contiene el token JWT generado.
        return new LoginResponse(token);

    }
}
