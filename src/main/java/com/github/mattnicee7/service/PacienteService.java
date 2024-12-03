package com.github.mattnicee7.service;

import com.github.mattnicee7.entities.Paciente;
import com.github.mattnicee7.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PacienteService {

    @Autowired
    private PacienteRepository pacienteRepository;

    public List<Paciente> findAll() {
        return pacienteRepository.findAll();
    }

    public Optional<Paciente> findById(Long id) {
        return pacienteRepository.findById(id);
    }

    public Optional<Paciente> findByCpf(String cpf) {
        return pacienteRepository.findByCpf(cpf);
    }

    public Paciente save(Paciente paciente) {
        if (pacienteRepository.existsByCpf(paciente.getCpf()))
            return pacienteRepository.findByCpf(paciente.getCpf()).get();
        return pacienteRepository.save(paciente);
    }

    public Paciente update(Long id, Paciente paciente) {
        if (pacienteRepository.existsById(id)) {
            paciente.setId(id);
            return pacienteRepository.save(paciente);
        }
        throw new RuntimeException("Paciente não encontrado.");
    }

    public void deleteById(Long id) {
        if (pacienteRepository.existsById(id)) {
            pacienteRepository.deleteById(id);
        } else {
            throw new RuntimeException("Paciente não encontrado.");
        }
    }
}

