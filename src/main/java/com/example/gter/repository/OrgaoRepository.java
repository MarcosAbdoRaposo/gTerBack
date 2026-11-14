package com.example.gter.repository;

import com.example.gter.model.Orgao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrgaoRepository extends JpaRepository<Orgao, java.util.UUID> {
    public boolean existsByNumCNPJ(String numCNPJ);




}
