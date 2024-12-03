package com.github.mattnicee7.service;

import com.github.mattnicee7.entities.Consulta;
import com.github.mattnicee7.repository.ConsultaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ConsultaService {

    @Autowired
    private ConsultaRepository consultaRepository;

    public List<Consulta> findAll() {
        return consultaRepository.findAll();
    }

    public Optional<Consulta> findById(Long id) {
        return consultaRepository.findById(id);
    }

    public Consulta save(Consulta consulta) {
        return consultaRepository.save(consulta);
    }

    public Consulta update(Long id, Consulta consulta) {
        if (consultaRepository.existsById(id)) {
            consulta.setId(id);
            return consultaRepository.save(consulta);
        }
        //
        throw new RuntimeException("Consulta não encontrada.");
    }

    public void deleteById(Long id) {
        if (consultaRepository.existsById(id)) {
            consultaRepository.deleteById(id);
        } else {
            //
            throw new RuntimeException("Consulta não encontrada.");
        }
    }
}

