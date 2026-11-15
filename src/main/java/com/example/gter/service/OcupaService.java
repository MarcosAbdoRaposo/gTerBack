package com.example.gter.service;

import com.example.gter.model.Ocupa;
import com.example.gter.repository.OcupaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class OcupaService {

    @Autowired
    private OcupaRepository ocupaRepository;

    public List<Ocupa> listarTodas() {
        return ocupaRepository.findAll();
    }

    public Ocupa buscarPorId(UUID id) {
        return ocupaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ocupação não encontrada com o ID: " + id));
    }

    public List<Ocupa> buscarPorTerceirizado(UUID ideTerceirizado) {
        return ocupaRepository.findByTerceirizadoIdeTerceirizado(ideTerceirizado);
    }

    public List<Ocupa> buscarPorPostoTrabalho(UUID idePostoTrabalho) {
        return ocupaRepository.findByPostoTrabalhoIdePostoTrabalho(idePostoTrabalho);
    }

    public List<Ocupa> buscarAtivasPorTerceirizado(UUID ideTerceirizado) {
        return ocupaRepository.findByTerceirizadoIdeTerceirizadoAndDatFimIsNull(ideTerceirizado);
    }

    public List<Ocupa> buscarAtivasPorPostoTrabalho(UUID idePostoTrabalho) {
        return ocupaRepository.findByPostoTrabalhoIdePostoTrabalhoAndDatFimIsNull(idePostoTrabalho);
    }

    @Transactional
    public Ocupa incluir(Ocupa ocupa) {
        // Validação opcional: garantir que não há sobreposição de alocação ativa se necessário
        return ocupaRepository.save(ocupa);
    }

    @Transactional
    public Ocupa atualizar(UUID id, Ocupa ocupaAtualizada) {
        Ocupa existente = buscarPorId(id);
        ocupaAtualizada.setIdeOcupa(existente.getIdeOcupa());
        return ocupaRepository.save(ocupaAtualizada);
    }

    @Transactional
    public void excluir(UUID id) {
        Ocupa existente = buscarPorId(id);
        ocupaRepository.delete(existente);
    }
}