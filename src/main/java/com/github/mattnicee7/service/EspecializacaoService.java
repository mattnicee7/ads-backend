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

    public Optional<Especializacao> findByEspecializacao(String especializacao) {
        return especializacaoRepository.findByEspecializacao(especializacao);
    }

    public Especializacao save(Especializacao especializacao) {
        return especializacaoRepository.findByEspecializacao(especializacao.getEspecializacao())
                .orElseGet(() -> especializacaoRepository.save(especializacao));
    }

    public Optional<Especializacao> update(Long id, Especializacao especializacao) {
        return especializacaoRepository.findById(id).map(existing -> {
            especializacao.setId(id);
            return especializacaoRepository.save(especializacao);
        });
    }

    public void deleteById(Long id) {
        especializacaoRepository.findById(id).map(especializacao -> {
            especializacaoRepository.deleteById(id);
            return true;
        });
    }
}
