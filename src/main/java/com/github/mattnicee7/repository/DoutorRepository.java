package com.github.mattnicee7.repository;

import com.github.mattnicee7.entities.Doutor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DoutorRepository extends JpaRepository<Doutor, Long> {

    boolean existsByCpf(String cpf);

    Optional<Doutor> findByCpf(String cpf);

}
