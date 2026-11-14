package com.example.gter.repository;


import com.example.gter.model.Contrato;
import com.example.gter.model.PostoTrabalho;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostoTrabalhoRepository extends JpaRepository<PostoTrabalho, java.util.UUID> {
    public boolean existsByContratoAndNomPostoTrabalho(Contrato contrato, String nomPostoTrabalho);


    @Query("SELECT p FROM PostoTrabalho p WHERE p.contrato.ideContrato = :ideContrato ORDER BY p.nomPostoTrabalho ASC")
    public List<PostoTrabalho> listarPorContratoOrdenadoPorNome(@Param("ideContrato") java.util.UUID ideContrato);
}
