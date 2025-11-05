package com.example.onepiece.controllers;

import com.example.onepiece.domain.DangerLevel;
import com.example.onepiece.domain.StatusMissao;
import com.example.onepiece.dtos.MissaoCreateDTO;
import com.example.onepiece.dtos.MissaoDTO;
import com.example.onepiece.mappers.MissaoMapper;
import com.example.onepiece.services.MissaoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/missoes")
public class MissaoController {

    private final MissaoService service;

    public MissaoController(MissaoService service) {
        this.service = service;
    }

    // Criar uma missão
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MissaoDTO criar(@Valid @RequestBody MissaoCreateDTO dto) {
        return MissaoMapper.toDTO(service.criar(dto.getPirataId(), MissaoMapper.toEntity(dto)));
    }

    // Atualizar missão
    @PutMapping("/{id}")
    public MissaoDTO atualizar(@PathVariable UUID id, @Valid @RequestBody MissaoCreateDTO dto) {
        return MissaoMapper.toDTO(service.atualizar(id, MissaoMapper.toEntity(dto)));
    }

    // Buscar por ID
    @GetMapping("/{id}")
    public MissaoDTO buscarPorId(@PathVariable UUID id) {
        return MissaoMapper.toDTO(service.buscarPorId(id));
    }

    // Listar todas
    @GetMapping
    public List<MissaoDTO> listarTodas() {
        return MissaoMapper.toDTOList(service.listarTodas());
    }

    // Buscar por nível de perigo
    @GetMapping("/danger/{nivel}")
    public List<MissaoDTO> buscarPorDanger(@PathVariable("nivel") DangerLevel nivel) {
        return MissaoMapper.toDTOList(service.buscarPorDanger(nivel));
    }

    // Buscar por status
    @GetMapping("/status/{status}")
    public List<MissaoDTO> buscarPorStatus(@PathVariable("status") StatusMissao status) {
        return MissaoMapper.toDTOList(service.buscarPorStatus(status));
    }
}
