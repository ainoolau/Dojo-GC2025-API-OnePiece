package com.example.onepiece.dtos;

import com.example.onepiece.domain.DangerLevel;
import com.example.onepiece.domain.StatusMissao;
import com.example.onepiece.domain.TipoMissao;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MissaoCreateDTO {

    @NotNull
    private DangerLevel danger;

    @NotNull
    private TipoMissao tipo;

    @NotNull
    private StatusMissao status;

    @NotNull
    private UUID pirataId;
}
