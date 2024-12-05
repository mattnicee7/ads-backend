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
        return pacienteRepository.findByCpf(paciente.getCpf())
                .orElseGet(() -> pacienteRepository.save(paciente));
    }

    public Optional<Paciente> update(Long id, Paciente paciente) {
        return pacienteRepository.findById(id).map(existingPaciente -> {
            paciente.setId(id);
            return pacienteRepository.save(paciente);
        });
    }

    public void deleteById(Long id) {
        pacienteRepository.findById(id).map(paciente -> {
            pacienteRepository.deleteById(id);
            return true;
        });
    }
}
