package com.github.mattnicee7.service;

import com.github.mattnicee7.dto.ResultadoConsultaRequestDTO;
import com.github.mattnicee7.entities.ResultadoConsulta;
import com.github.mattnicee7.repository.ConsultaRepository;
import com.github.mattnicee7.repository.ResultadoConsultaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ResultadoConsultaService {

    @Autowired
    private ResultadoConsultaRepository resultadoConsultaRepository;

    @Autowired
    private ConsultaRepository consultaRepository;

    public ResultadoConsulta save(ResultadoConsulta resultadoConsulta) {
        return resultadoConsultaRepository.save(resultadoConsulta);
    }

    public Optional<ResultadoConsulta> save(ResultadoConsultaRequestDTO request) {
        return consultaRepository.findById(request.getConsulta())
                .filter(consulta -> consulta.getResultadoConsulta() == null)
                .map(consulta -> {
                    ResultadoConsulta resultadoConsulta = new ResultadoConsulta();
                    resultadoConsulta.setConsulta(consulta);
                    resultadoConsulta.setRemedio(request.getRemedio());
                    resultadoConsulta.setInstrucoes(request.getInstrucoes());

                    ResultadoConsulta savedResultado = resultadoConsultaRepository.save(resultadoConsulta);

                    consulta.setResultadoConsulta(savedResultado);
                    consultaRepository.save(consulta);

                    return savedResultado;
                });
    }



}
