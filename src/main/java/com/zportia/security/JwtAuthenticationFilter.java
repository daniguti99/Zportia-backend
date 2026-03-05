package com.zportia.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtUtil jwtUtil;

    public JwtAuthenticationFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    // Filtro que intercepta las solicitudes HTTP para validar el token JWT y establecer la autenticación en el contexto de seguridad
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // Si la solicitud es para el endpoint de inicio de sesión ("/auth/login"), se permite que la solicitud pase sin validar el token JWT
        String uri = request.getRequestURI();

        if (uri.equals("/auth/login") || uri.equals("/auth/register")) {
            filterChain.doFilter(request, response);
            return;
        }


        //Obtenemos el encabezado "Authorization" de la solicitud HTTP, que se espera que contenga el token JWT en el formato "Bearer <token>"
        String authHeader = request.getHeader("Authorization");

        // Si el encabezado no es nulo y comienza con "Bearer ", se extrae el token JWT del encabezado
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            // El token JWT se extrae del encabezado eliminando el prefijo "Bearer "
            String token = authHeader.substring(7);
            // Se valida el token JWT utilizando el metodo validateToken del JwtUtil.
            if (jwtUtil.validateToken(token)) {
                // Si el token es válido, se extraen el nombre de usuario y el rol del token utilizando los metodos extractUsername y extractRole del JwtUtil.
                String username = jwtUtil.extractUsername(token);
                String role = jwtUtil.extractRole(token);
                // Se crea un objeto UsernamePasswordAuthenticationToken con el nombre de usuario, sin credenciales (null) y una lista de autoridades que contiene el rol del usuario (con el prefijo "ROLE_").
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(username, null, List.of(new SimpleGrantedAuthority("ROLE_" + role)));
                // Se establece el objeto de autenticación en el contexto de seguridad de Spring Security utilizando SecurityContextHolder.getContext().setAuthentication(authToken),
                // lo que permite que la autenticación del usuario esté disponible para el resto de la cadena de filtros y para los controladores que manejen las solicitudes.
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        // Continuamos con la cadena de filtros, permitiendo que la solicitud HTTP se procese normalmente después de validar el token JWT y establecer la autenticación.
        filterChain.doFilter(request, response);
    }
}
