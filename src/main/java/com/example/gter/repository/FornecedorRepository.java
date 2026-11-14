package com.example.gter.repository;

import com.example.gter.model.Fornecedor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FornecedorRepository extends JpaRepository<Fornecedor, java.util.UUID> {
    public boolean existsByNumCNPJ(String numCNPJ);
}
