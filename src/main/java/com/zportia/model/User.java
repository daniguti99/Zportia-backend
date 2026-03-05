package com.zportia.model;

import jakarta.persistence.*;
import lombok.*;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "Usuario") // nombre real de la tabla en MySQL
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nombre")
    private String name;

    @Column(name = "correo")
    private String email;

    @Column(name = "contraseña")
    private String password;

    @Column(name = "foto")
    private String photo;

    @Column(name = "deportes")
    private String sports;

    @Column(name = "nivel")
    private String level;

    @Column(name = "privado")
    private Boolean isPrivate;

    @Column(name = "rol")
    private String role;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Post> posts;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Comment> comments;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Reaction> reactions;

    @OneToMany(mappedBy = "originUser", cascade = CascadeType.ALL)
    private List<SocialRelation> socialRelationsOrigin;

    @OneToMany(mappedBy = "targetUser", cascade = CascadeType.ALL)
    private List<SocialRelation> socialRelationsTarget;

    // Para la autorización, se asigna un rol al usuario y se devuelve una colección de autoridades basada en ese rol.
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + role));
    }
     // Para la autenticación, el email se utiliza como nombre de usuario y la contraseña se obtiene del campo correspondiente.
    @Override
    public @Nullable String getPassword() {
        return this.password;
    }

    // En este caso, el email se utiliza como nombre de usuario para la autenticación.
    @Override
    public String getUsername() {
        return this.email;
    }

    // Para simplificar, asumimos que todas las cuentas están activas, no bloqueadas y no expiradas.

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


}
