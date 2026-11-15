package com.example.gter.controller;

import com.example.gter.model.Ocupa;
import com.example.gter.service.OcupaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/ocupa")
public class OcupaController {

    @Autowired
    private OcupaService ocupaService;

    @GetMapping
    public ResponseEntity<List<Ocupa>> listarTodas() {
        List<Ocupa> lista = ocupaService.listarTodas();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ocupa> buscarPorId(@PathVariable UUID id) {
        Ocupa ocupa = ocupaService.buscarPorId(id);
        return ResponseEntity.ok(ocupa);
    }

    @GetMapping("/terceirizado/{ideTerceirizado}")
    public ResponseEntity<List<Ocupa>> buscarPorTerceirizado(@PathVariable UUID ideTerceirizado) {
        List<Ocupa> lista = ocupaService.buscarPorTerceirizado(ideTerceirizado);
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/posto-trabalho/{idePostoTrabalho}")
    public ResponseEntity<List<Ocupa>> buscarPorPostoTrabalho(@PathVariable UUID idePostoTrabalho) {
        List<Ocupa> lista = ocupaService.buscarPorPostoTrabalho(idePostoTrabalho);
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/terceirizado/{ideTerceirizado}/ativas")
    public ResponseEntity<List<Ocupa>> buscarAtivasPorTerceirizado(@PathVariable UUID ideTerceirizado) {
        List<Ocupa> lista = ocupaService.buscarAtivasPorTerceirizado(ideTerceirizado);
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/posto-trabalho/{idePostoTrabalho}/ativas")
    public ResponseEntity<List<Ocupa>> buscarAtivasPorPostoTrabalho(@PathVariable UUID idePostoTrabalho) {
        List<Ocupa> lista = ocupaService.buscarAtivasPorPostoTrabalho(idePostoTrabalho);
        return ResponseEntity.ok(lista);
    }

    @PostMapping
    public ResponseEntity<Ocupa> salvar(@RequestBody Ocupa ocupa) {
        Ocupa novaOcupa = ocupaService.incluir(ocupa);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaOcupa);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Ocupa> atualizar(@PathVariable UUID id, @RequestBody Ocupa ocupa) {
        Ocupa atualizada = ocupaService.atualizar(id, ocupa);
        return ResponseEntity.ok(atualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable UUID id) {
        ocupaService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}