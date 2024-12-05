package com.github.mattnicee7.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tb_consulta")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Consulta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "doutor_id", nullable = false)
    private Doutor doutor;

    @ManyToOne
    @JoinColumn(name = "paciente_id", nullable = false)
    private Paciente paciente;

    private String sintoma;

    @OneToOne(mappedBy = "consulta", cascade = CascadeType.ALL)
    private ResultadoConsulta resultadoConsulta;

}
