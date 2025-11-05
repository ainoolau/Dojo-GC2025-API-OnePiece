package com.example.onepiece.services;

import com.example.onepiece.domain.DangerLevel;
import com.example.onepiece.domain.StatusMissao;
import com.example.onepiece.entities.Missao;
import com.example.onepiece.entities.Pirata;
import com.example.onepiece.repositories.MissaoRepository;
import com.example.onepiece.repositories.PirataRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.UUID;

@Service
public class MissaoService {

    private final MissaoRepository missaoRepository;
    private final PirataRepository pirataRepository;

    public MissaoService(MissaoRepository missaoRepository, PirataRepository pirataRepository) {
        this.missaoRepository = missaoRepository;
        this.pirataRepository = pirataRepository;
    }

    // Criar missão
    @Transactional
    public Missao criar(UUID pirataId, Missao missao) {
        Pirata pirata = pirataRepository.findById(pirataId)
                .orElseThrow(() -> new EntityNotFoundException("Pirata não encontrado"));

        validarCamposObrigatorios(missao);
        missao.setPirata(pirata);

        return missaoRepository.save(missao);
    }

    // Atualizar missão
    @Transactional
    public Missao atualizar(UUID id, Missao novosDados) {
        Missao existente = missaoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Missão não encontrada"));

        validarCamposObrigatorios(novosDados);

        existente.setDanger(novosDados.getDanger());
        existente.setTipo(novosDados.getTipo());
        existente.setStatus(novosDados.getStatus());

        return missaoRepository.save(existente);
    }

    // Buscar por ID
    @Transactional(readOnly = true)
    public Missao buscarPorId(UUID id) {
        return missaoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Missão não encontrada"));
    }

    // Listar todas
    @Transactional(readOnly = true)
    public List<Missao> listarTodas() {
        return missaoRepository.findAll();
    }

    // Buscar por nível de perigo
    @Transactional(readOnly = true)
    public List<Missao> buscarPorDanger(DangerLevel danger) {
        return missaoRepository.findByDanger(danger);
    }

    // Buscar por status
    @Transactional(readOnly = true)
    public List<Missao> buscarPorStatus(StatusMissao status) {
        return missaoRepository.findByStatus(status);
    }

    // Validações
    private void validarCamposObrigatorios(Missao missao) {
        if (missao.getDanger() == null)
            throw new IllegalArgumentException("Danger Level é obrigatório");
        if (missao.getTipo() == null)
            throw new IllegalArgumentException("Tipo de missão é obrigatório");
        if (missao.getStatus() == null)
            throw new IllegalArgumentException("Status é obrigatório");
    }
}
