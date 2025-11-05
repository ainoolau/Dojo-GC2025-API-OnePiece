package com.example.onepiece.dtos;

import com.example.onepiece.domain.RacaPirata;
import com.example.onepiece.domain.StatusPirata;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PirataCreateDTO {

    @NotBlank
    private String nome;

    @NotNull
    private RacaPirata raca;

    @NotBlank
    private String tripulacao;

    @NotNull
    private StatusPirata status;
}
