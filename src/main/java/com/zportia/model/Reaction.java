package com.zportia.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Reaccion") // nombre real de la tabla en MySQL
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Reaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "tipo")
    private String type;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "publicacion_id")
    private Post post;


}
