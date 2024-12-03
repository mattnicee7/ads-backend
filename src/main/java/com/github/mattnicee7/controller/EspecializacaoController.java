package com.github.mattnicee7.controller;

import com.github.mattnicee7.entities.Especializacao;
import com.github.mattnicee7.service.EspecializacaoService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/{id}")
    public ResponseEntity<Especializacao> getById(@PathVariable Long id) {
        return especializacaoService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Especializacao> create(@RequestBody Especializacao especializacao) {
        return ResponseEntity.ok(especializacaoService.save(especializacao));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Especializacao> update(@PathVariable Long id, @RequestBody Especializacao especializacao) {
        try {
            return ResponseEntity.ok(especializacaoService.update(id, especializacao));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try {
            especializacaoService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

}

