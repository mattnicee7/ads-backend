package com.github.mattnicee7.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tb_resultadoconsulta")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ResultadoConsulta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    @OneToOne
    @JoinColumn(name = "consulta_id", nullable = false)
    @JsonIgnore
    private Consulta consulta;

    private String remedio;

    private String instrucoes;

}
