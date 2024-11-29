package com.github.mattnicee7.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "tb_doutor")
@AllArgsConstructor
@Getter
@NoArgsConstructor
public class Doutor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @ManyToMany
    @JoinTable(
            name = "doutor_especializacao",
            joinColumns = @JoinColumn(name = "doutor_id"),
            inverseJoinColumns = @JoinColumn(name = "especializacao_id")
    )
    private List<Especializacao> especializacoes;

}
