package com.github.mattnicee7.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ConsultaRequestDTO {

    private Long doutorId;
    private Long pacienteId;
    private String sintoma;

}
