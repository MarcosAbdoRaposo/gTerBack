package com.example.gter.repository;

import com.example.gter.model.Terceirizado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface TerceirizadoRepository extends JpaRepository<Terceirizado, UUID> {

    // Busca exata pelo CPF (campo único na entidade)
    Optional<Terceirizado> findByNumCPF(String numCPF);

    // Verifica se já existe um terceirizado cadastrado com o CPF informado
    boolean existsByNumCPF(String numCPF);

    // Busca por parte do nome ignorando maiúsculas/minúsculas
    List<Terceirizado> findByNomTerceirizadoContainingIgnoreCase(String nomTerceirizado);


}