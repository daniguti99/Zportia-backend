package com.zportia.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {
    //Clave secreta para firmar los tokens JWT (en producción, esta clave debe ser segura y no compartida)
    private final String SECRET = "mi_clave_super_secreta_para_jwt_que_debe_ser_larga";
    //Tiempo de expiración del token (en este caso, 1 hora)
    private final long EXPIRATION_TIME = 1000 * 60 * 60; // 1 hora en milisegundos

    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(SECRET.getBytes());
    }

    //Genera un token JWT para un usuario dado su nombre de usuario y rol
    public String generateToken(String username, String role) {
        return Jwts.builder()
                //Establece el sujeto del token (en este caso, el nombre de usuario)
                .setSubject(username)
                //Agrega el rol como una reclamación personalizada
                .claim("role", role)
                //Establece la fecha de emisión del token
                .setIssuedAt(new Date())
                //Establece la fecha de expiración del token
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                //Firma el token con la clave secreta utilizando el algoritmo HS256
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    // Extraer username
    public String extractUsername(String token) {
        return getClaims(token).getSubject();
    }

    //Extraer rol
    public String extractRole(String token) {
        //La reclamación personalizada "role" se extrae del token utilizando el metodo getClaims y se devuelve como una cadena
        return (String) getClaims(token).get("role");
    }

    //Validar el token (verificar firma y expiración)
    public boolean validateToken(String token) {
        try {
            //Si el token es válido, se pueden extraer las reclamaciones sin lanzar una excepción
            getClaims(token);
            return true;
        } catch (Exception e) {
            //Si el token es inválido (firma incorrecta, expirado, etc.), se lanzará una excepción y se devolverá false
            return false;
        }
    }
    //Metodo privado para extraer los elementos del token JWT
    private Claims getClaims(String token) {
        //El metodo getClaims utiliza el parser de JJWT para validar la firma del token y
        // extraer las reclamaciones (claims) contenidas en el token JWT.
        // Si el token es válido, se devuelve el cuerpo de las reclamaciones; de lo contrario, se lanzará una excepción.
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }


}
