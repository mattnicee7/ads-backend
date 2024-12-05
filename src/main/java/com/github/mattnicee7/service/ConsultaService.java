package com.github.mattnicee7.service;

import com.github.mattnicee7.entities.Consulta;
import com.github.mattnicee7.entities.Doutor;
import com.github.mattnicee7.entities.Paciente;
import com.github.mattnicee7.exception.ObjectNotFoundException;
import com.github.mattnicee7.repository.ConsultaRepository;
import com.github.mattnicee7.repository.DoutorRepository;
import com.github.mattnicee7.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ConsultaService {

    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private DoutorRepository doutorRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    public List<Consulta> findAll() {
        return consultaRepository.findAll();
    }

    public Optional<Consulta> findById(Long id) {
        return consultaRepository.findById(id);
    }

    public Consulta save(Consulta consulta) {
        if (consulta.getPaciente() != null) {
            consulta.getPaciente().addConsulta(consulta);
        }
        return consultaRepository.save(consulta);
    }


    public Consulta save(Long doutorId, Long pacienteId, String sintoma) {
        Doutor doutor = doutorRepository.findById(doutorId)
                .orElseThrow(() -> new RuntimeException("Doutor não encontrado com ID: " + doutorId));

        Paciente paciente = pacienteRepository.findById(pacienteId)
                .orElseThrow(() -> new RuntimeException("Paciente não encontrado com ID: " + pacienteId));

        Consulta consulta = new Consulta();
        consulta.setDoutor(doutor);
        consulta.setPaciente(paciente);
        consulta.setSintoma(sintoma);

        paciente.addConsulta(consulta);

        return consultaRepository.save(consulta);
    }


    public Consulta update(Long id, Consulta consulta) {
        if (!consultaRepository.existsById(id)) {
            throw new ObjectNotFoundException("Consulta com ID " + id + " não encontrada.");
        }
        consulta.setId(id);
        return consultaRepository.save(consulta);
    }

    public void deleteById(Long id) {
        Consulta consulta = consultaRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Consulta com ID " + id + " não encontrada."));

        if (consulta.getPaciente() != null) {
            consulta.getPaciente().removeConsulta(consulta);
        }

        consultaRepository.deleteById(id);
    }

}
