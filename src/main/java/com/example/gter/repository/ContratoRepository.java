package com.example.gter.repository;

import com.example.gter.model.Contrato;

import com.example.gter.model.Orgao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface ContratoRepository extends JpaRepository<Contrato, java.util.UUID> {
    boolean existsByAnoContratoAndNumContratoAndOrgao(int anoContrato, int numContrato, Orgao orgao);


    @Query("SELECT COUNT(c) > 0 FROM Contrato c WHERE c.anoContrato = :anoContrato AND c.numContrato = :numContrato AND c.orgao.ideOrgao = :ideOrgao")
    boolean existeContrato(
            @Param("anoContrato") int anoContrato,
            @Param("numContrato") int numContrato,
            @Param("ideOrgao") java.util.UUID ideOrgao
    );

    // Busca contratos pelo ID do Orgao associado
    List<Contrato> findByOrgao_IdeOrgao(UUID ideOrgao);

}
