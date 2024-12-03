package com.github.mattnicee7.service;

import com.github.mattnicee7.entities.Doutor;
import com.github.mattnicee7.entities.Especializacao;
import com.github.mattnicee7.repository.DoutorRepository;
import com.github.mattnicee7.repository.EspecializacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DoutorService {

    @Autowired
    private DoutorRepository doutorRepository;

    @Autowired
    private EspecializacaoService especializacaoService;

    public List<Doutor> findAll() {
        return doutorRepository.findAll();
    }

    public Optional<Doutor> findById(Long id) {
        return doutorRepository.findById(id);
    }

    public Doutor save(Doutor doutor) {
        List<Especializacao> especializacoes = doutor.getEspecializacoes().stream().map(
                e -> especializacaoService.findById(e.getId()).get()
                ).toList();

        doutor.setEspecializacoes(especializacoes);

        return doutorRepository.save(doutor);
    }

    public Doutor update(Long id, Doutor doutor) {
        if (doutorRepository.existsById(id)) {
            doutor.setId(id);
            return doutorRepository.save(doutor);
        }
        throw new RuntimeException("Doutor não encontrado.");
    }

    public void deleteById(Long id) {
        if (doutorRepository.existsById(id)) {
            doutorRepository.deleteById(id);
        } else {
            throw new RuntimeException("Doutor não encontrado.");
        }
    }
}
