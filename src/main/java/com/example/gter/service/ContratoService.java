package com.example.gter.service;

import com.example.gter.model.Contrato;
import com.example.gter.repository.ContratoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class ContratoService {

    private final ContratoRepository contratoRepository;

    // Injeção de dependência via construtor (prática recomendada no Spring)
    public ContratoService(ContratoRepository contratoRepository) {
        this.contratoRepository = contratoRepository;
    }

    @Transactional(readOnly = true)
    public List<Contrato> listarTodos() {
        return contratoRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Contrato buscarPorId(UUID ideContrato) {
        return contratoRepository.findById(ideContrato)
                .orElseThrow(() -> new EntityNotFoundException("Órgão não encontrado com o ID: " + ideContrato));
    }

    @Transactional
    public Contrato incluir(Contrato contrato) {
        // Utiliza o método existsByAnoContratoAndNumContratoAndOrgao definido no ContratoRepository
        if ( contratoRepository.existeContrato(contrato.getAnoContrato(), contrato.getNumContrato(), contrato.getOrgao().getIdeOrgao())) {
            throw new IllegalArgumentException("Já existe um contrato com este mesmo número e ano cadastrado neste órgao." + contrato.getFornecedor().getNomFornecedor());
        }
        return contratoRepository.save(contrato);
    }

    @Transactional
    public Contrato atualizar(Contrato contratoAtualizado) {
        UUID ideContrato = contratoAtualizado.getIdeContrato();
        Contrato contratoExistente = buscarPorId(ideContrato);

        // Verifica se o CNPJ foi alterado e se o novo CNPJ já pertence a outro registro
        if ( (contratoExistente.getAnoContrato() != contratoAtualizado.getAnoContrato()
                || contratoExistente.getNumContrato() != contratoAtualizado.getNumContrato()
                || contratoExistente.getOrgao().getIdeOrgao() != contratoAtualizado.getOrgao().getIdeOrgao())
                && contratoRepository.existeContrato(contratoAtualizado.getAnoContrato(), contratoAtualizado.getNumContrato(), contratoAtualizado.getOrgao().getIdeOrgao())) {
            throw new IllegalArgumentException("Já existe outro contrato cadastrado com este número. " );
        }

        contratoExistente.setNumContrato(contratoAtualizado.getNumContrato());
        contratoExistente.setAnoContrato(contratoAtualizado.getAnoContrato());
        contratoExistente.setFornecedor(contratoAtualizado.getFornecedor());
        contratoExistente.setDatInicioVigencia(contratoAtualizado.getDatInicioVigencia());
        contratoExistente.setDatFimVigencia(contratoAtualizado.getDatFimVigencia());

        return contratoRepository.save(contratoAtualizado);
    }

    @Transactional
    public void excluir(UUID ideContrato) {
        if (!contratoRepository.existsById(ideContrato)) {
            throw new EntityNotFoundException("Órgão não encontrado com o ID: " + ideContrato);
        }

        //TODO Opcional: Verifique se existem Postos de Trabalho vinculados antes de excluir
        // (Será necessário ter um PostoTrabalhoRepository injetado no service)
        // if (PostoTrabalhoRepository.existsByContrato(contrato)) {
        //     throw new IllegalStateException("Não é possível excluir o Contrato  pois existem postos de trabalho vinculados.");
        // }
        contratoRepository.deleteById(ideContrato);
    }
}

