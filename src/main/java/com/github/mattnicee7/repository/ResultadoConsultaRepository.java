package com.github.mattnicee7.repository;

import com.github.mattnicee7.entities.Consulta;
import com.github.mattnicee7.entities.ResultadoConsulta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ResultadoConsultaRepository extends JpaRepository<ResultadoConsulta, Long> {

    Optional<ResultadoConsulta> findByConsulta(Consulta consulta);

}

