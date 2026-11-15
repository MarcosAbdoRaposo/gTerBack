package com.example.gter.controller;

import com.example.gter.model.Terceirizado;
import com.example.gter.service.TerceirizadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/terceirizado")
public class TerceirizadoController {

    @Autowired
    private TerceirizadoService terceirizadoService;

    @GetMapping
    public ResponseEntity<List<Terceirizado>> listarTodos() {
        List<Terceirizado> lista = terceirizadoService.listarTodos();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Terceirizado> buscarPorId(@PathVariable UUID id) {
        Terceirizado terceirizado = terceirizadoService.buscarPorId(id);
        return ResponseEntity.ok(terceirizado);
    }

    @GetMapping("/cpf/{numCPF}")
    public ResponseEntity<Terceirizado> buscarPorCpf(@PathVariable String numCPF) {
        Terceirizado terceirizado = terceirizadoService.buscarPorCpf(numCPF);
        return ResponseEntity.ok(terceirizado);
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<Terceirizado>> buscarPorNome(@RequestParam String nome) {
        List<Terceirizado> lista = terceirizadoService.buscarPorNome(nome);
        return ResponseEntity.ok(lista);
    }

    @PostMapping
    public ResponseEntity<Terceirizado> salvar(@RequestBody Terceirizado terceirizado) {
        Terceirizado novoTerceirizado = terceirizadoService.salvar(terceirizado);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoTerceirizado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Terceirizado> atualizar(@PathVariable UUID id, @RequestBody Terceirizado terceirizado) {
        Terceirizado atualizado = terceirizadoService.atualizar(id, terceirizado);
        return ResponseEntity.ok(atualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable UUID id) {
        terceirizadoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}