package com.zportia.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "RelacionSocial") // nombre real de la tabla en MySQL
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SocialRelation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "tipo")
    private String type; // follow, block, etc.

    @ManyToOne
    @JoinColumn(name = "usuario_origen_id")
    private User originUser; // usuario que inicia la relación

    @ManyToOne
    @JoinColumn(name = "usuario_destino_id")
    private User targetUser; // usuario que recibe la relación



}
