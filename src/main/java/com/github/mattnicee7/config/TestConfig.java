package com.github.mattnicee7.config;

import com.github.mattnicee7.entities.*;
import com.github.mattnicee7.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.List;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

    @Autowired
    private DoutorService doutorService;

    @Autowired
    private PacienteService pacienteService;

    @Autowired
    private ConsultaService consultaService;

    @Autowired
    private EspecializacaoService especializacaoService;

    @Autowired
    private ResultadoConsultaService resultadoConsultaService;

    @Override
    public void run(String... args) {
        Especializacao especializacao1 = especializacaoService.save(new Especializacao(null, "Coração"));
        Especializacao especializacao2 = especializacaoService.save(new Especializacao(null, "Dente"));
        Especializacao especializacao3 = especializacaoService.save(new Especializacao(null, "Fígado"));

        Doutor doutor1 = doutorService.save(new Doutor(null, "Matheus", "89132849031", List.of(especializacao1)));
        Doutor doutor2 = doutorService.save(new Doutor(null, "Ana", "19374912831", List.of(especializacao2, especializacao3)));

        Paciente paciente1 = pacienteService.save(new Paciente(null, "Fernando", "13750283910"));
        Paciente paciente2 = pacienteService.save(new Paciente(null, "Carlos", "27301938405"));

        Consulta consulta1 = consultaService.save(new Consulta(null, doutor1, paciente1, "dor de barriga", null));
        Consulta consulta2 = consultaService.save(new Consulta(null, doutor2, paciente2, "dor no rim", null));

        ResultadoConsulta resultado1 = resultadoConsultaService.save(
                new ResultadoConsulta(null, consulta1, "Dorflex", "Tome a cada 8h"));
        ResultadoConsulta resultado2 = resultadoConsultaService.save(
                new ResultadoConsulta(null, consulta2, "Vitamina D", "Tome todo dia pela manhã"));

        consulta1.setResultadoConsulta(resultado1);
        consulta2.setResultadoConsulta(resultado2);

        consultaService.save(consulta1);
        consultaService.save(consulta2);
    }
}
