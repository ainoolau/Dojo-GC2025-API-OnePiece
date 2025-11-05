package com.example.onepiece.entities;

import com.example.onepiece.domain.RacaPirata;
import com.example.onepiece.domain.StatusPirata;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "pirata")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Pirata {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String nome;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RacaPirata raca;

    @Column(nullable = false)
    private String tripulacao;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusPirata status;

    @OneToMany(
            mappedBy = "pirata",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    @Builder.Default
    private List<Missao> missoes = new ArrayList<>();


}
