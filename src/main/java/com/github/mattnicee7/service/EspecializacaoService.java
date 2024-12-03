package com.github.mattnicee7.service;

import com.github.mattnicee7.entities.Especializacao;
import com.github.mattnicee7.repository.EspecializacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EspecializacaoService {

    @Autowired
    private EspecializacaoRepository especializacaoRepository;

    public List<Especializacao> findAll() {
        return especializacaoRepository.findAll();
    }

    public Optional<Especializacao> findById(Long id) {
        return especializacaoRepository.findById(id);
    }

    public Especializacao save(Especializacao especializacao) {
        // evita criar duplicado com o mesmo nome.
        if (especializacaoRepository.existsByEspecializacao(especializacao.getEspecializacao()))
            return especializacaoRepository.findByEspecializacao(especializacao.getEspecializacao()).get();

        return especializacaoRepository.save(especializacao);
    }

    public Especializacao update(Long id, Especializacao especializacao) {
        if (especializacaoRepository.existsById(id)) {
            especializacao.setId(id);
            return especializacaoRepository.save(especializacao);
        }
        throw new RuntimeException("Especialização não encontrada.");
    }

    public void deleteById(Long id) {
        if (especializacaoRepository.existsById(id)) {
            especializacaoRepository.deleteById(id);
        } else {
            throw new RuntimeException("Especialização não encontrada.");
        }
    }
}

