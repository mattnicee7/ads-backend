package com.github.mattnicee7.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_paciente")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String cpf;

    @OneToMany(mappedBy = "paciente", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Consulta> consultas = new ArrayList<>();

    public Paciente(Long id, String nome, String cpf) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
    }

    public void addConsulta(Consulta consulta) {
        if (!consultas.contains(consulta)) {
            consultas.add(consulta);
            consulta.setPaciente(this);
        }
    }

    public void removeConsulta(Consulta consulta) {
        if (consultas.contains(consulta)) {
            consultas.remove(consulta);
            consulta.setPaciente(null);
        }
    }
}