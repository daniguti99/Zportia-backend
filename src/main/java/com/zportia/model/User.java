package com.zportia.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "Usuario") // nombre real de la tabla en MySQL
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

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

    @Column(name = "esAdmin") // NUEVO CAMPO
    private Boolean isAdmin;

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


}
