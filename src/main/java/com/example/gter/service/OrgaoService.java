package com.example.gter.service;

import com.example.gter.model.Orgao;
import com.example.gter.repository.OrgaoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class OrgaoService {

    private final OrgaoRepository orgaoRepository;

    // Injeção de dependência via construtor (prática recomendada no Spring)
    public OrgaoService(OrgaoRepository orgaoRepository) {
        this.orgaoRepository = orgaoRepository;
    }

    @Transactional(readOnly = true)
    public List<Orgao> listarTodos() {
        return orgaoRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Orgao buscarPorId(UUID ideOrgao) {
        return orgaoRepository.findById(ideOrgao)
                .orElseThrow(() -> new EntityNotFoundException("Órgão não encontrado com o ID: " + ideOrgao));
    }

    @Transactional
    public Orgao incluir(Orgao orgao) {
        // Utiliza o método existsByNumCNPJ definido no OrgaoRepository
        if (orgaoRepository.existsByNumCNPJ(orgao.getNumCNPJ())) {
            throw new IllegalArgumentException("Já existe um órgão cadastrado com o CNPJ: " + orgao.getNumCNPJ());
        }
        return orgaoRepository.save(orgao);
    }

    @Transactional
    public Orgao atualizar(Orgao orgaoAtualizado) {
        UUID ideOrgao = orgaoAtualizado.getIdeOrgao();
        Orgao orgaoExistente = buscarPorId(ideOrgao);

        // Verifica se o CNPJ foi alterado e se o novo CNPJ já pertence a outro registro
        if (!orgaoExistente.getNumCNPJ().equals(orgaoAtualizado.getNumCNPJ())
                && orgaoRepository.existsByNumCNPJ(orgaoAtualizado.getNumCNPJ())) {
            throw new IllegalArgumentException("Já existe outro órgão cadastrado com o CNPJ: " + orgaoAtualizado.getNumCNPJ());
        }

        orgaoExistente.setNomOrgao(orgaoAtualizado.getNomOrgao());
        orgaoExistente.setNumCNPJ(orgaoAtualizado.getNumCNPJ());

        return orgaoRepository.save(orgaoExistente);
    }

    @Transactional
    public void excluir(UUID ideOrgao) {
        if (!orgaoRepository.existsById(ideOrgao)) {
            throw new EntityNotFoundException("Órgão não encontrado com o ID: " + ideOrgao);
        }

        //TODO Opcional: Verifique se existem contratos vinculados antes de deletar
        // (Você precisaria ter um contratoRepository injetado no service)
        // if (contratoRepository.existsByOrgao(orgao)) {
        //     throw new IllegalStateException("Não é possível excluir o órgão pois existem contratos vinculados.");
        // }
        orgaoRepository.deleteById(ideOrgao);
    }
}

