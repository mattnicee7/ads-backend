package com.github.mattnicee7.repository;

import com.github.mattnicee7.entities.Especializacao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EspecializacaoRepository extends JpaRepository<Especializacao, Long> {

    Optional<Especializacao> findByEspecializacao(String especializacao);

    boolean existsByEspecializacao(String especializacao);

}
