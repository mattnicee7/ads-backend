package com.github.mattnicee7.controller;

import com.github.mattnicee7.dto.ConsultaRequestDTO;
import com.github.mattnicee7.dto.ResultadoConsultaRequestDTO;
import com.github.mattnicee7.entities.Consulta;
import com.github.mattnicee7.entities.ResultadoConsulta;
import com.github.mattnicee7.exception.InvalidOperationException;
import com.github.mattnicee7.exception.ObjectNotFoundException;
import com.github.mattnicee7.service.ConsultaService;
import com.github.mattnicee7.service.ResultadoConsultaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/consultas")
public class ConsultaController {

    @Autowired
    private ConsultaService consultaService;

    @Autowired
    private ResultadoConsultaService resultadoConsultaService;

    @GetMapping
    public ResponseEntity<List<Consulta>> getAll() {
        return ResponseEntity.ok(consultaService.findAll());
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Consulta> getById(@PathVariable Long id) {
        return consultaService.findById(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ObjectNotFoundException("Consulta com ID " + id + " não encontrada"));
    }

    @PostMapping
    public ResponseEntity<Consulta> create(@RequestBody ConsultaRequestDTO consultaRequestDTO) {
        Consulta savedConsulta = consultaService.save(
                consultaRequestDTO.getDoutorId(),
                consultaRequestDTO.getPacienteId(),
                consultaRequestDTO.getSintoma()
        );
        return ResponseEntity.ok(savedConsulta);
    }

    @PutMapping("/id/{id}/resultados")
    public ResponseEntity<?> addResultadoConsulta(
            @PathVariable Long id,
            @RequestBody ResultadoConsultaRequestDTO resultadoConsultaRequestDTO
    ) {
        resultadoConsultaRequestDTO.setConsulta(id);

        Optional<Consulta> optionalConsulta = consultaService.findById(id);
        if (optionalConsulta.isEmpty())
            throw new ObjectNotFoundException("Consulta com ID " + id + " não encontrada");

        Optional<ResultadoConsulta> optionalResultado = resultadoConsultaService.save(resultadoConsultaRequestDTO);
        if (optionalResultado.isEmpty())
            throw new InvalidOperationException("Já existe um resultado associado à consulta com ID " + id);

        Consulta consulta = optionalConsulta.get();
        consulta.setResultadoConsulta(optionalResultado.get());
        consultaService.update(id, consulta);

        return ResponseEntity.ok(consulta);
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        Consulta consulta = consultaService.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Consulta com ID " + id + " não encontrada."));

        if (consulta.getPaciente() != null)
            consulta.getPaciente().getConsultas().remove(consulta);

        consultaService.deleteById(id);

        return ResponseEntity.ok("Consulta com ID " + id + " deletada com sucesso.");
    }

}
