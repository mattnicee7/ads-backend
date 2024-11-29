package com.github.mattnicee7.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_consulta")
@AllArgsConstructor
@Getter
@NoArgsConstructor
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

    @OneToOne(mappedBy = "consulta", cascade = CascadeType.ALL)
    @Setter
    private ResultadoConsulta resultadoConsulta;

}

