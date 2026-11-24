package com.example.gter.service;

import com.example.gter.exception.NaoEncontradoException;
import com.example.gter.model.Terceirizado;
import com.example.gter.repository.TerceirizadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class TerceirizadoService {

    @Autowired
    private TerceirizadoRepository terceirizadoRepository;

    public List<Terceirizado> listarTodos() {
        return terceirizadoRepository.findAll();
    }

    public Terceirizado buscarPorId(UUID id) {
        return terceirizadoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Terceirizado não encontrado com o ID: " + id));
    }

    public Terceirizado buscarPorCpf(String numCPF) {
        return terceirizadoRepository.findByNumCPF(numCPF)
                .orElseThrow(() -> new NaoEncontradoException("Terceirizado não encontrado com o CPF: " + numCPF));
    }

    public List<Terceirizado> buscarPorNome(String nome) {
        return terceirizadoRepository.findByNomTerceirizadoContainingIgnoreCase(nome);
    }

    public List<Terceirizado> buscarPorContrato(UUID ideContrato) {
        return terceirizadoRepository.findDistinctByContrato(ideContrato);
    }




    @Transactional
    public Terceirizado salvar(Terceirizado terceirizado) {
        if (terceirizado.getIdeTerceirizado() == null && terceirizadoRepository.existsByNumCPF(terceirizado.getNumCPF())) {
            throw new RuntimeException("Já existe um terceirizado cadastrado com o CPF: " + terceirizado.getNumCPF());
        }
        return terceirizadoRepository.save(terceirizado);
    }

    @Transactional
    public Terceirizado atualizar(UUID id, Terceirizado terceirizadoAtualizado) {
        Terceirizado existente = buscarPorId(id);

        // Se o CPF foi alterado, verifica se o novo CPF já pertence a outro registro
        if (!existente.getNumCPF().equals(terceirizadoAtualizado.getNumCPF())
                && terceirizadoRepository.existsByNumCPF(terceirizadoAtualizado.getNumCPF())) {
            throw new RuntimeException("O CPF informado já está em uso por outro terceirizado.");
        }

        terceirizadoAtualizado.setIdeTerceirizado(existente.getIdeTerceirizado());
        return terceirizadoRepository.save(terceirizadoAtualizado);
    }

    @Transactional
    public void deletar(UUID id) {
        Terceirizado existente = buscarPorId(id);
        terceirizadoRepository.delete(existente);
    }
}