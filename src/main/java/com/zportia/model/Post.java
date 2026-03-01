package com.zportia.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "Publicacion") // nombre real de la tabla en MySQL
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "contenido")
    private String content;

    @Column(name = "fecha")
    private LocalDateTime date;

    @Column(name = "ubicacion")
    private String location;

    @Column(name = "multimedia")
    private String media;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private User user;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<Comment> comments;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<Reaction> reactions;

}
