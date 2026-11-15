package com.example.gter.repository;

import com.example.gter.model.Ocupa;
import com.example.gter.model.PostoTrabalho;
import com.example.gter.model.Terceirizado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Repository
public interface OcupaRepository extends JpaRepository<Ocupa, UUID> {

    // Busca todas as ocupações de um terceirizado (por objeto ou por ID)
    List<Ocupa> findByTerceirizado(Terceirizado terceirizado);
    List<Ocupa> findByTerceirizadoIdeTerceirizado(UUID ideTerceirizado);

    // Busca todas as ocupações de um posto de trabalho (por objeto ou por ID)
    List<Ocupa> findByPostoTrabalho(PostoTrabalho postoTrabalho);
    List<Ocupa> findByPostoTrabalhoIdePostoTrabalho(UUID idePostoTrabalho);

    // Busca ocupações ativas de um terceirizado ou posto específico
    List<Ocupa> findByTerceirizadoIdeTerceirizadoAndDatFimIsNull(UUID ideTerceirizado);
    List<Ocupa> findByPostoTrabalhoIdePostoTrabalhoAndDatFimIsNull(UUID idePostoTrabalho);


}