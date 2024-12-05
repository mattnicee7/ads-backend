package com.github.mattnicee7.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tb_especializacao")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Especializacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String especializacao;

}
