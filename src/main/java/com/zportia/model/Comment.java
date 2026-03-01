package com.zportia.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Comentario") // nombre real de la tabla en MySQL
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "texto")
    private String text;

    @Column(name = "fecha")
    private LocalDateTime date;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "publicacion_id")
    private Post post;


}
