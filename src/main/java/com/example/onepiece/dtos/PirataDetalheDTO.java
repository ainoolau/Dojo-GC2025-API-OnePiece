package com.example.onepiece.dtos;

import com.example.onepiece.domain.RacaPirata;
import com.example.onepiece.domain.StatusPirata;
import lombok.*;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PirataDetalheDTO {
    private UUID id;
    private String nome;
    private RacaPirata raca;
    private String tripulacao;
    private StatusPirata status;
    private List<MissaoResumoDTO> missoes;
}
