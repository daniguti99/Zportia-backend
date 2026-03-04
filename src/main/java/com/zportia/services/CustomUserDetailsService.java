package com.zportia.services;

import com.zportia.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

// Servicio personalizado para cargar detalles de usuario desde la base de datos
@Service
public class CustomUserDetailsService implements UserDetailsService {
    // Inyectamos el UserRepository para acceder a los datos de usuario
    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Sobrescribimos el metodo para cargar el usuario por su email
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado con email: " + email));
    }
}
