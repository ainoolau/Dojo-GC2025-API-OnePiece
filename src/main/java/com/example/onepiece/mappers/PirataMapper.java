package com.example.onepiece.mappers;

import com.example.onepiece.dtos.PirataCreateDTO;
import com.example.onepiece.dtos.PirataDTO;
import com.example.onepiece.dtos.PirataDetalheDTO;
import com.example.onepiece.entities.Pirata;
import java.util.List;
import java.util.stream.Collectors;

public class PirataMapper {

    // DTO >> Entity (criação ou atualização)
    public static Pirata toEntity(PirataCreateDTO dto) {
        if (dto == null) return null;

        return Pirata.builder()
                .nome(dto.getNome())
                .raca(dto.getRaca())
                .tripulacao(dto.getTripulacao())
                .status(dto.getStatus())
                .build();
    }

    // Entity >> DTO (resposta)
    public static PirataDTO toDTO(Pirata entity) {
        if (entity == null) return null;

        return PirataDTO.builder()
                .id(entity.getId())
                .nome(entity.getNome())
                .raca(entity.getRaca())
                .tripulacao(entity.getTripulacao())
                .status(entity.getStatus())
                .build();
    }

    // Entity >> DTO (detalhe - usado para exibir o Pirata com suas missões)
    public static PirataDetalheDTO toDetalheDTO(Pirata entity) {
        if (entity == null) return null;
        return PirataDetalheDTO.builder()
                .id(entity.getId())
                .nome(entity.getNome())
                .raca(entity.getRaca())
                .tripulacao(entity.getTripulacao())
                .status(entity.getStatus())
                .missoes(entity.getMissoes() == null ? List.of()
                        : entity.getMissoes().stream()
                        .map(MissaoMapper::toResumoDTO)
                        .collect(Collectors.toList()))
                .build();
    }


    // Lista de entities >> Lista de DTOs
    public static List<PirataDTO> toDTOList(List<Pirata> entities) {
        if (entities == null) return List.of();
        return entities.stream().map(PirataMapper::toDTO).collect(Collectors.toList());
    }
}
