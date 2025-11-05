package com.example.onepiece.repositories;

import com.example.onepiece.domain.RacaPirata;
import com.example.onepiece.entities.Pirata;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.EntityGraph;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface PirataRepository extends JpaRepository<Pirata, UUID> {

    // Busca piratas por raça (ex: todos os MINK)
    List<Pirata> findByRaca(RacaPirata raca);

    // Carrega o pirata já com a coleção de missões (evita LazyInitialization e N+1)
    @EntityGraph(attributePaths = {"missoes"})
    Optional<Pirata> findWithMissoesById(UUID id);
}
