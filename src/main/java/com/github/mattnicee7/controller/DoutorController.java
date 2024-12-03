package com.github.mattnicee7.controller;

import com.github.mattnicee7.entities.Doutor;
import com.github.mattnicee7.service.DoutorService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/{id}")
    public ResponseEntity<Doutor> getById(@PathVariable Long id) {
        return doutorService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Doutor> create(@RequestBody Doutor doutor) {
        return ResponseEntity.ok(doutorService.save(doutor));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Doutor> update(@PathVariable Long id, @RequestBody Doutor doutor) {
        try {
            return ResponseEntity.ok(doutorService.update(id, doutor));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try {
            doutorService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}

