package com.github.mattnicee7.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_resultadoconsulta")
@AllArgsConstructor
@Getter
@NoArgsConstructor
public class ResultadoConsulta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "consulta_id", nullable = false)
    private Consulta consulta;

    private String remedio;

    private String instrucoes;

}

