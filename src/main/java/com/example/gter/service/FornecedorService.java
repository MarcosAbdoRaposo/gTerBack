package com.example.gter.service;

import com.example.gter.model.Fornecedor;
import com.example.gter.repository.FornecedorRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class FornecedorService {

    private final FornecedorRepository FornecedorRepository;

    // Injeção de dependência via construtor (prática recomendada no Spring)
    public FornecedorService(FornecedorRepository FornecedorRepository) {
        this.FornecedorRepository = FornecedorRepository;
    }

    @Transactional(readOnly = true)
    public List<Fornecedor> listarTodos() {
        return FornecedorRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Fornecedor buscarPorId(UUID ideFornecedor) {
        return FornecedorRepository.findById(ideFornecedor)
                .orElseThrow(() -> new EntityNotFoundException("Órgão não encontrado com o ID: " + ideFornecedor));
    }

    @Transactional
    public Fornecedor incluir(Fornecedor Fornecedor) {
        // Utiliza o método existsByNumCNPJ definido no FornecedorRepository
        if (FornecedorRepository.existsByNumCNPJ(Fornecedor.getNumCNPJ())) {
            throw new IllegalArgumentException("Já existe um órgão cadastrado com o CNPJ: " + Fornecedor.getNumCNPJ());
        }
        return FornecedorRepository.save(Fornecedor);
    }

    @Transactional
    public Fornecedor atualizar(Fornecedor FornecedorAtualizado) {
        UUID ideFornecedor = FornecedorAtualizado.getIdeFornecedor();
        Fornecedor FornecedorExistente = buscarPorId(ideFornecedor);

        // Verifica se o CNPJ foi alterado e se o novo CNPJ já pertence a outro registro
        if (!FornecedorExistente.getNumCNPJ().equals(FornecedorAtualizado.getNumCNPJ())
                && FornecedorRepository.existsByNumCNPJ(FornecedorAtualizado.getNumCNPJ())) {
            throw new IllegalArgumentException("Já existe outro órgão cadastrado com o CNPJ: " + FornecedorAtualizado.getNumCNPJ());
        }

        FornecedorExistente.setNomFornecedor(FornecedorAtualizado.getNomFornecedor());
        FornecedorExistente.setNumCNPJ(FornecedorAtualizado.getNumCNPJ());

        return FornecedorRepository.save(FornecedorExistente);
    }

    @Transactional
    public void excluir(UUID ideFornecedor) {
        if (!FornecedorRepository.existsById(ideFornecedor)) {
            throw new EntityNotFoundException("Órgão não encontrado com o ID: " + ideFornecedor);
        }
        FornecedorRepository.deleteById(ideFornecedor);
    }
}

