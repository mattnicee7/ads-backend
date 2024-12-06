package com.github.mattnicee7.controller;

import com.github.mattnicee7.entities.Doutor;
import com.github.mattnicee7.exception.CpfInvalidoException;
import com.github.mattnicee7.exception.InvalidOperationException;
import com.github.mattnicee7.exception.ObjectNotFoundException;
import com.github.mattnicee7.service.DoutorService;
import com.github.mattnicee7.util.CpfChecker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/doutores")
public class DoutorController {

    @Autowired
    private DoutorService doutorService;

    @GetMapping
    public ResponseEntity<List<Doutor>> getAll() {
        return ResponseEntity.ok(doutorService.findAll());
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Doutor> getById(@PathVariable Long id) {
        return doutorService.findById(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ObjectNotFoundException("Doutor com ID " + id + " não encontrado."));
    }

    @GetMapping("/cpf/{cpf}")
    public ResponseEntity<Doutor> getByCpf(@PathVariable String cpf) {
        return doutorService.findByCpf(cpf)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ObjectNotFoundException("Doutor com CPF " + cpf + " não encontrado."));
    }

    @PostMapping
    public ResponseEntity<Doutor> create(@RequestBody Doutor doutor) {
        return doutorService.findByCpf(doutor.getCpf())
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.ok(doutorService.save(doutor)));
    }

    @PutMapping("/id/{id}")
    public ResponseEntity<Doutor> update(@PathVariable Long id, @RequestBody Doutor doutor) {
        return doutorService.update(id, doutor)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ObjectNotFoundException("Doutor com ID " + id + " não encontrado."));
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        if (doutorService.findById(id).isEmpty())
            throw new ObjectNotFoundException("Doutor com ID " + id + " não encontrado.");

        try {
            doutorService.deleteById(id);
            return ResponseEntity.ok("Doutor com ID " + id + " deletado com sucesso.");
        } catch (DataIntegrityViolationException e) {
            throw new InvalidOperationException(
                    "Doutor com ID " + id + " não pode ser deletado, pois está associado a uma ou mais consultas."
            );
        }

    }

}
