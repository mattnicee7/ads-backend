package com.github.mattnicee7.controller;

import com.github.mattnicee7.entities.Especializacao;
import com.github.mattnicee7.exception.InvalidOperationException;
import com.github.mattnicee7.exception.ObjectNotFoundException;
import com.github.mattnicee7.service.EspecializacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/especializacoes")
public class EspecializacaoController {

    @Autowired
    private EspecializacaoService especializacaoService;

    @GetMapping
    public ResponseEntity<List<Especializacao>> getAll() {
        return ResponseEntity.ok(especializacaoService.findAll());
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Especializacao> getById(@PathVariable Long id) {
        return especializacaoService.findById(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ObjectNotFoundException("Especialização com ID " + id + " não encontrada."));
    }

    @PostMapping
    public ResponseEntity<Especializacao> create(@RequestBody Especializacao especializacao) {
        Especializacao savedEspecializacao = especializacaoService.save(especializacao);
        return ResponseEntity.ok(savedEspecializacao);
    }

    @PutMapping("/id/{id}")
    public ResponseEntity<Especializacao> update(@PathVariable Long id, @RequestBody Especializacao especializacao) {
        return especializacaoService.update(id, especializacao)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ObjectNotFoundException("Especialização com ID " + id + " não encontrada."));
    }

    @GetMapping("/search")
    public ResponseEntity<Especializacao> searchByEspecializacao(@RequestParam String especializacao) {
        return especializacaoService.findByEspecializacao(especializacao)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ObjectNotFoundException("Especialização " + especializacao + " não encontrada."));
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        if (especializacaoService.findById(id).isEmpty())
            throw new ObjectNotFoundException("Especialização com ID " + id + " não encontrada.");

        try {
            especializacaoService.deleteById(id);
            return ResponseEntity.ok("Especialização com ID " + id + " deletada com sucesso.");
        } catch (DataIntegrityViolationException e) {
            throw new InvalidOperationException(
                    "Não é possível deletar a especialização com ID " + id + " pois está associada a um ou mais doutores.");
        }

    }

}
