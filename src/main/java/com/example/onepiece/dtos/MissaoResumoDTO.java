package com.example.onepiece.dtos;

import com.example.onepiece.domain.DangerLevel;
import com.example.onepiece.domain.StatusMissao;
import com.example.onepiece.domain.TipoMissao;
import lombok.*;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MissaoResumoDTO {
    private UUID id;
    private DangerLevel danger;
    private TipoMissao tipo;
    private StatusMissao status;
}
