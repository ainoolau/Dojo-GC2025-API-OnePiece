package com.example.onepiece.repositories;

import com.example.onepiece.domain.DangerLevel;
import com.example.onepiece.domain.StatusMissao;
import com.example.onepiece.entities.Missao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface MissaoRepository extends JpaRepository<Missao, UUID> {

    // Busca missões por nível de perigo
    List<Missao> findByDanger(DangerLevel danger);

    // Busca missões por status (ex: EM_ANDAMENTO)
    List<Missao> findByStatus(StatusMissao status);

}
