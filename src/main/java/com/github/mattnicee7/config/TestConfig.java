package com.github.mattnicee7.config;

import com.github.mattnicee7.entities.*;
import com.github.mattnicee7.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.Arrays;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

    @Autowired
    private DoutorRepository doutorRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private EspecializacaoRepository especializacaoRepository;

    @Autowired
    private ResultadoConsultaRepository resultadoConsultaRepository;

    @Override
    public void run(String... args) throws Exception {
        Especializacao especializacao1 = new Especializacao(null, "Coração");
        Especializacao especializacao2 = new Especializacao(null, "Dente");
        Especializacao especializacao3 = new Especializacao(null, "Fígado");

        especializacaoRepository.saveAll(Arrays.asList(especializacao1, especializacao2, especializacao3));

        Doutor doutor1 = new Doutor(null, "Matheus", Arrays.asList(especializacao1));
        Doutor doutor2 = new Doutor(null, "Ana", Arrays.asList(especializacao2, especializacao3));

        doutorRepository.saveAll(Arrays.asList(doutor1, doutor2));

        Paciente paciente1 = new Paciente(null, "Fernando", "13750283910");
        Paciente paciente2 = new Paciente(null, "Carlos", "27301938405");

        pacienteRepository.saveAll(Arrays.asList(paciente1, paciente2));

        Consulta consulta1 = new Consulta(null, doutor1, paciente1, null);
        Consulta consulta2 = new Consulta(null, doutor2, paciente2, null);

        consultaRepository.saveAll(Arrays.asList(consulta1, consulta2));

        ResultadoConsulta resultadoConsulta1 = new ResultadoConsulta(null, consulta1, "Dorflex", "Tome a cada 8h");
        ResultadoConsulta resultadoConsulta2 = new ResultadoConsulta(null, consulta2, "Vitamina D", "Tome todo dia pela manhã");

        resultadoConsultaRepository.saveAll(Arrays.asList(resultadoConsulta1, resultadoConsulta2));

        consulta1.setResultadoConsulta(resultadoConsulta1);
        consulta2.setResultadoConsulta(resultadoConsulta2);

        consultaRepository.saveAll(Arrays.asList(consulta1, consulta2));
    }


}
