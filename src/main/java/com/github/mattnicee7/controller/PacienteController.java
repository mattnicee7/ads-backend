package com.github.mattnicee7.controller;

import com.github.mattnicee7.entities.Paciente;
import com.github.mattnicee7.exception.CpfInvalidoException;
import com.github.mattnicee7.service.PacienteService;
import com.github.mattnicee7.util.CpfChecker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    @Autowired
    private PacienteService pacienteService;

    @GetMapping
    public ResponseEntity<List<Paciente>> getAll() {
        return ResponseEntity.ok(pacienteService.findAll());
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Paciente> getById(@PathVariable Long id) {
        return pacienteService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/cpf/{cpf}")
    public ResponseEntity<Paciente> getByCpf(@PathVariable String cpf) {
        if (!CpfChecker.check(cpf))
            throw new CpfInvalidoException("CPF inválido");

        return pacienteService.findByCpf(cpf)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Paciente> create(@RequestBody Paciente paciente) {
        if (!CpfChecker.check(paciente.getCpf()))
            throw new CpfInvalidoException("CPF inválido");

        Paciente savedPaciente = pacienteService.save(paciente);
        return ResponseEntity.ok(savedPaciente);
    }

    @PutMapping("/id/{id}")
    public ResponseEntity<Paciente> update(@PathVariable Long id, @RequestBody Paciente paciente) {
        if (!CpfChecker.check(paciente.getCpf()))
            throw new CpfInvalidoException("CPF inválido");

        return pacienteService.update(id, paciente)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        if (pacienteService.findById(id).isEmpty())
            return ResponseEntity.notFound().build();

        pacienteService.deleteById(id);
        return ResponseEntity.ok("Paciente com ID " + id + " deletado com sucesso.");
    }

}
