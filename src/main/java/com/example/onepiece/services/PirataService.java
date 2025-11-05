package com.example.onepiece.services;

import com.example.onepiece.domain.RacaPirata;
import com.example.onepiece.entities.Pirata;
import com.example.onepiece.infra.exceptions.ResourceNotFoundException;
import com.example.onepiece.repositories.PirataRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.UUID;

@Service
public class PirataService {

    private final PirataRepository pirataRepository;

    public PirataService(PirataRepository pirataRepository) {
        this.pirataRepository = pirataRepository;
    }

    // Criar pirata
    @Transactional
    public Pirata criar(Pirata pirata) {
        validarCamposObrigatorios(pirata);
        return pirataRepository.save(pirata);
    }

    // Atualizar pirata
    @Transactional
    public Pirata atualizar(UUID id, Pirata novosDados) {
        Pirata existente = pirataRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Pirata não encontrado"));

        validarCamposObrigatorios(novosDados);

        existente.setNome(novosDados.getNome());
        existente.setRaca(novosDados.getRaca());
        existente.setTripulacao(novosDados.getTripulacao());
        existente.setStatus(novosDados.getStatus());

        return pirataRepository.save(existente);
    }

    // Buscar por ID
    @Transactional(readOnly = true)
    public Pirata buscarPorId(UUID id) {
        return pirataRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pirata não encontrado"));
    }

    // Buscar por raça
    @Transactional(readOnly = true)
    public List<Pirata> buscarPorRaca(RacaPirata raca) {
        return pirataRepository.findByRaca(raca);
    }

    // Listar todos
    @Transactional(readOnly = true)
    public List<Pirata> listarTodos() {
        return pirataRepository.findAll();
    }

    // Deletar pirata
    @Transactional
    public void deletar(UUID id) {
        if (!pirataRepository.existsById(id)) {
            throw new EntityNotFoundException("Pirata não encontrado");
        }
        pirataRepository.deleteById(id);
    }

    // Buscar pirata por ID com suas missões (detalhado)
    @Transactional(readOnly = true)
    public Pirata buscarDetalhePorId(UUID id) {
        return pirataRepository.findWithMissoesById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pirata não encontrado"));
    }

    // Validações básicas
    private void validarCamposObrigatorios(Pirata pirata) {
        if (pirata.getNome() == null || pirata.getNome().isBlank())
            throw new IllegalArgumentException("Nome é obrigatório");
        if (pirata.getTripulacao() == null || pirata.getTripulacao().isBlank())
            throw new IllegalArgumentException("Tripulação é obrigatória");
        if (pirata.getRaca() == null)
            throw new IllegalArgumentException("Raça é obrigatória");
        if (pirata.getStatus() == null)
            throw new IllegalArgumentException("Status é obrigatório");
    }
}
