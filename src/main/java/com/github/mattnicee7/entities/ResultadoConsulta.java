package com.github.mattnicee7.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_resultadoconsulta")
@AllArgsConstructor
@Getter
@NoArgsConstructor
@Setter
public class ResultadoConsulta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "consulta_id", nullable = false)
    @JsonIgnore
    private Consulta consulta;

    private String remedio;

    private String instrucoes;

}

