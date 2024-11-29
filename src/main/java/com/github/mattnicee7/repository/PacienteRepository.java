package com.github.mattnicee7.repository;

import com.github.mattnicee7.entities.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {
}
