package com.github.mattnicee7.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ResultadoConsultaRequestDTO {

    private Long consulta;
    private String remedio;
    private String instrucoes;

}
