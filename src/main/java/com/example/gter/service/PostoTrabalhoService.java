package com.example.gter.service;

import com.example.gter.model.Contrato;
import com.example.gter.model.PostoTrabalho;
import com.example.gter.repository.PostoTrabalhoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class PostoTrabalhoService {

    private final PostoTrabalhoRepository postoTrabalhoRepository;

    public PostoTrabalhoService(PostoTrabalhoRepository postoTrabalhoRepository) {
        this.postoTrabalhoRepository = postoTrabalhoRepository;
    }

    @Transactional(readOnly = true)
    public List<PostoTrabalho> listarTodos() {
        return postoTrabalhoRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<PostoTrabalho> listarPostoTrabalhoContrato(java.util.UUID ideContrato) {
        return postoTrabalhoRepository.listarPorContratoOrdenadoPorNome(ideContrato);
    }

    @Transactional(readOnly = true)
    public PostoTrabalho buscarPorId(UUID idePostoTrabalho) {
        return postoTrabalhoRepository.findById(idePostoTrabalho)
                .orElseThrow(() -> new EntityNotFoundException("Posto de Trabalho não encontrado com o ID: " + idePostoTrabalho));
    }

    @Transactional
    public PostoTrabalho incluir(PostoTrabalho postoTrabalho) {
        if (postoTrabalhoRepository.existsByContratoAndNomPostoTrabalho(postoTrabalho.getContrato(), postoTrabalho.getNomPostoTrabalho())) {
            throw new IllegalArgumentException("Já existe um posto de trabalho com este nome cadastrado para este contrato.");
        }
        return postoTrabalhoRepository.save(postoTrabalho);
    }

    @Transactional
    public PostoTrabalho atualizar(PostoTrabalho postoTrabalhoAtualizado) {
        UUID ide = postoTrabalhoAtualizado.getIdePostoTrabalho();
        PostoTrabalho existente = buscarPorId(ide);

        if ((!existente.getContrato().equals(postoTrabalhoAtualizado.getContrato()) ||
                !existente.getNomPostoTrabalho().equals(postoTrabalhoAtualizado.getNomPostoTrabalho()))
                && postoTrabalhoRepository.existsByContratoAndNomPostoTrabalho(postoTrabalhoAtualizado.getContrato(), postoTrabalhoAtualizado.getNomPostoTrabalho())) {
            throw new IllegalArgumentException("Já existe outro posto de trabalho com este nome cadastrado para este contrato.");
        }

        existente.setContrato(postoTrabalhoAtualizado.getContrato());
        existente.setNomPostoTrabalho(postoTrabalhoAtualizado.getNomPostoTrabalho());
        existente.setQtdPostoTrabalho(postoTrabalhoAtualizado.getQtdPostoTrabalho());

        return postoTrabalhoRepository.save(existente);
    }

    @Transactional
    public void excluir(UUID idePostoTrabalho) {
        if (!postoTrabalhoRepository.existsById(idePostoTrabalho)) {
            throw new EntityNotFoundException("Posto de Trabalho não encontrado com o ID: " + idePostoTrabalho);
        }
        postoTrabalhoRepository.deleteById(idePostoTrabalho);
    }




}