package com.github.mattnicee7.service;

import com.github.mattnicee7.entities.Doutor;
import com.github.mattnicee7.entities.Especializacao;
import com.github.mattnicee7.exception.CpfInvalidoException;
import com.github.mattnicee7.exception.ObjectNotFoundException;
import com.github.mattnicee7.repository.DoutorRepository;
import com.github.mattnicee7.util.CpfChecker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public Optional<Doutor> findByCpf(String cpf) {
        if (!CpfChecker.check(cpf))
            throw new CpfInvalidoException("CPF inválido");

        return doutorRepository.findByCpf(cpf);
    }

    public Doutor save(Doutor doutor) {
        if (!CpfChecker.check(doutor.getCpf()))
            throw new CpfInvalidoException("CPF inválido");

        List<Especializacao> especializacoes = doutor.getEspecializacoes().stream()
                .map(e -> especializacaoService.findById(e.getId())
                        .orElseThrow(() -> new ObjectNotFoundException("Especialização com ID " + e.getId() + " não encontrada.")))
                .toList();

        doutor.setEspecializacoes(especializacoes);
        return doutorRepository.save(doutor);
    }

    public boolean existsByCpf(String cpf) {
        return doutorRepository.existsByCpf(cpf);
    }

    public Optional<Doutor> update(Long id, Doutor doutor) {
        if (!CpfChecker.check(doutor.getCpf()))
            throw new CpfInvalidoException("CPF inválido");

        return doutorRepository.findById(id).map(existingDoutor -> {
            doutor.setId(id);
            return doutorRepository.save(doutor);
        });
    }

    public void deleteById(Long id) {
        doutorRepository.findById(id).map(doutor -> {
            doutorRepository.deleteById(id);
            return true;
        });
    }

}
