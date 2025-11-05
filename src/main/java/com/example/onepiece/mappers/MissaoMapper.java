package com.example.onepiece.mappers;

import com.example.onepiece.dtos.MissaoCreateDTO;
import com.example.onepiece.dtos.MissaoDTO;
import com.example.onepiece.entities.Missao;
import java.util.List;
import java.util.stream.Collectors;

public class MissaoMapper {

    // DTO >> Entity (criação ou atualização)
    public static Missao toEntity(MissaoCreateDTO dto) {
        if (dto == null) return null;

        return Missao.builder()
                .danger(dto.getDanger())
                .tipo(dto.getTipo())
                .status(dto.getStatus())
                .build();
    }

    // Entity >> DTO (resposta)
    public static MissaoDTO toDTO(Missao entity) {
        if (entity == null) return null;

        return MissaoDTO.builder()
                .id(entity.getId())
                .danger(entity.getDanger())
                .tipo(entity.getTipo())
                .status(entity.getStatus())
                .pirataId(entity.getPirata() != null ? entity.getPirata().getId() : null)
                .build();
    }

    // Lista de entities >> Lista de DTOs
    public static List<MissaoDTO> toDTOList(List<Missao> entities) {
        if (entities == null) return List.of();
        return entities.stream().map(MissaoMapper::toDTO).collect(Collectors.toList());
    }
}
