package com.example.onepiece.controllers;

import com.example.onepiece.domain.RacaPirata;
import com.example.onepiece.dtos.PirataCreateDTO;
import com.example.onepiece.dtos.PirataDTO;
import com.example.onepiece.mappers.PirataMapper;
import com.example.onepiece.services.PirataService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.example.onepiece.dtos.PirataDetalheDTO;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/piratas")
public class PirataController {

    private final PirataService service;

    public PirataController(PirataService service) {
        this.service = service;
    }

    // Criar um novo pirata
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PirataDTO criar(@Valid @RequestBody PirataCreateDTO dto) {
        return PirataMapper.toDTO(service.criar(PirataMapper.toEntity(dto)));
    }

    // Atualizar pirata existente
    @PutMapping("/{id}")
    public PirataDTO atualizar(@PathVariable UUID id, @Valid @RequestBody PirataCreateDTO dto) {
        return PirataMapper.toDTO(service.atualizar(id, PirataMapper.toEntity(dto)));
    }

    // Buscar por ID
    @GetMapping("/{id}")
    public PirataDTO buscarPorId(@PathVariable UUID id) {
        return PirataMapper.toDTO(service.buscarPorId(id));
    }

    // Buscar por raça
    @GetMapping("/raca/{raca}")
    public List<PirataDTO> buscarPorRaca(@PathVariable RacaPirata raca) {
        return PirataMapper.toDTOList(service.buscarPorRaca(raca));
    }

    // Listar todos
    @GetMapping
    public List<PirataDTO> listarTodos() {
        return PirataMapper.toDTOList(service.listarTodos());
    }

    // Apagar um pirata
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable UUID id) {
        service.deletar(id);
    }

    // Buscar pirata por ID e exibir suas missões (detalhe completo)
    @GetMapping("/{id}/detalhe")
    public PirataDetalheDTO buscarDetalhe(@PathVariable UUID id) {
        return PirataMapper.toDetalheDTO(service.buscarDetalhePorId(id));
    }
}
